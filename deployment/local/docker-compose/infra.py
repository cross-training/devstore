import os
import subprocess
import time
import argparse
from dotenv import load_dotenv

load_dotenv()
db_url = os.getenv('DB_URL')
db_database = os.getenv('DB_DATABASE')
db_username = os.getenv('DB_USERNAME')
db_password = os.getenv('DB_PASSWORD')
catalog_db_username = os.getenv('CATALOG_DB_USERNAME')
catalog_db_password = os.getenv('CATALOG_DB_PASSWORD')
catalog_db_schema = os.getenv('CATALOG_DB_SCHEMA')
rating_db_username = os.getenv('RATING_DB_USERNAME')
rating_db_password = os.getenv('RATING_DB_PASSWORD')
rating_db_schema= os.getenv('RATING_DB_SCHEMA')    


def run_command(command, cwd=None):
    """Execute a shell command and return the output."""
    result = subprocess.run([command], shell=True, capture_output=True, text=True, check=True, env=os.environ, cwd=cwd)
    if result.returncode != 0:
        print(f"Error executing command: {command}")
        print(result.stderr)
        raise subprocess.CalledProcessError(result.returncode, command)
    return result.stdout

def validate_environment_variables():
    """Validate that all necessary environment variables are set."""
    if not all([db_url, db_database, db_username, db_password]):
        raise ValueError("Some necessary environment variables are missing.")

def register_environment_variables():
    """Register environment variables for the Flyway configuration."""
    os.environ["DB_URL"] = db_url
    os.environ["DB_USERNAME"] = db_username
    os.environ["DB_PASSWORD"] = db_password

def script_create_user(username, password):
    """Create a user in the PostgreSQL database."""
    return f'''
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT *
        FROM pg_user
        WHERE usename = '{username}'
    ) THEN
        CREATE USER {username} WITH PASSWORD '{password}';
    END IF;
END $$;
'''

def script_create_schema(schema, username):
    """Create a user in the PostgreSQL database."""
    return f'''
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_namespace
        WHERE nspname = '{schema}'
    ) THEN
        CREATE SCHEMA {schema} AUTHORIZATION {username};
    END IF;
END $$;
'''

def execute_db_script(script):
    """Execute a script in the PostgreSQL database."""
    try:
        command = f'''docker exec -it postgres psql -U {db_database} -d {db_database} -c "{script}"'''
        run_command(command)
    except subprocess.CalledProcessError as e:
        print(f"An error occurred: {e}")


def flyway_migrate(cwd,url,username,password,schema):
    """Execute the Flyway migration."""
    command= f'''flyway -url={url} -user={username} -password={password} -schemas={schema} -locations=filesystem:./sql migrate'''
    run_command(command, cwd) 

def up_database():
    """Start the Docker Compose infrastructure."""   
    # 1. Start Database
    print("Starting Database...")
    run_command('docker-compose -p devstore up -d postgres')
    # Wait a bit to ensure containers are ready
    time.sleep(10)

    # 2. Execute an initial script in PostgreSQL
    print("Creating users and schemas...")   
    execute_db_script(f"CREATE USER {catalog_db_username} WITH PASSWORD '{catalog_db_password}';")
    execute_db_script(f"CREATE SCHEMA {catalog_db_schema} AUTHORIZATION {catalog_db_username};")
    execute_db_script(f"CREATE USER {rating_db_username} WITH PASSWORD '{rating_db_password}';")
    execute_db_script(f"CREATE SCHEMA {rating_db_schema} AUTHORIZATION {rating_db_username};")
    # execute_db_script(script_create_user('catalog', 'catalog'))
    # execute_db_script(script_create_schema('catalog', 'catalog'))
    # execute_db_script(script_create_user('rating', 'rating'))
    # execute_db_script(script_create_schema('rating', 'rating'))

    # # 3. Execute Flyway in the Spring Boot services
    print("Creating initial DDL and DML ...")
    current_dir = os.path.dirname(os.path.abspath(__file__))
    three_levels_up = os.path.dirname(os.path.dirname(os.path.dirname(current_dir)))
    services_path = os.path.join(three_levels_up, 'services')
    flyway_migrate(os.path.join(services_path, "catalog-service/src/main/resources/db"),db_url,catalog_db_username,catalog_db_password,catalog_db_schema)
    flyway_migrate(os.path.join(services_path, "rating-service/src/main/resources/db"),db_url,rating_db_username,rating_db_password,rating_db_schema)

def up_infrastructure_services():
    """Start the Docker Compose infrastructure services."""
    print("Starting Infrastructures Services...")
    run_command('docker-compose -p devstore up -d config-server discovery-server api-gateway')

def up_business_services():
    """Start the business services."""
    print("Starting Business Services...")
    run_command('docker-compose -p devstore up -d catalog-service rating-service')

def up_monitoring_services():
    """Start the monitoring services."""
    print("Starting Monitoring Services...")
    run_command('docker-compose -p devstore up -d prometheus grafana')

def end_infrastructure():
    """Stop the Docker Compose infrastructure."""
    print("Stopping Database...")
    run_command('docker-compose -p devstore down')

def main(action):
    """Main function to execute the script."""
    try:   
        if action == 'up':
            validate_environment_variables()
            register_environment_variables()
            up_database()
            up_infrastructure_services()
        elif action == 'down':
            end_infrastructure()
        else:
            print("Unrecognized action. It should be 'up' or 'down'.")
    except subprocess.CalledProcessError as e:
        print(f"An error occurred: {e}")

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='Sample script')
    parser.add_argument('action', choices=['up', 'down'], help="Action to perform ('up' or 'down')")
    args = parser.parse_args()
    main(args.action)
