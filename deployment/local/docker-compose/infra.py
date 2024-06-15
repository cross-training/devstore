import os
import subprocess
import time
import argparse
from dotenv import load_dotenv

load_dotenv()
db_url = os.getenv('DB_URL')
db_database = os.getenv('DB_DATABASE')
db_user = os.getenv('DB_USER')
db_password = os.getenv('DB_PASSWORD')
catalog_db_user = os.getenv('CATALOG_DB_USER')
catalog_db_password = os.getenv('CATALOG_DB_PASSWORD')
catalog_db_schema = os.getenv('CATALOG_DB_SCHEMA')
rating_db_user = os.getenv('RATING_DB_USER')
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
    if not all([db_url, db_database, db_user, db_password]):
        raise ValueError("Some necessary environment variables are missing.")

def register_environment_variables():
    """Register environment variables for the Flyway configuration."""
    os.environ["DB_URL"] = db_url
    os.environ["DB_USERNAME"] = db_user
    os.environ["DB_PASSWORD"] = db_password

def script_create_user(user, password):
    """Create a user in the PostgreSQL database."""
    return f'''
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT *
        FROM pg_user
        WHERE usename = '{user}'
    ) THEN
        CREATE USER {user} WITH PASSWORD '{password}';
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


def flyway_migrate(cwd,url,user,password,schema):
    """Execute the Flyway migration."""
    command= f'''flyway -url={url} -user={user} -password={password} -schemas={schema} -locations=filesystem:./sql migrate'''
    run_command(command, cwd)
    # flyway -url=jdbc:postgresql://localhost:5432/devstore -user=catalog -password=catalog -schemas=catalog -locations=filesystem:./sql migrate

def up_postgres():
    """Start the Docker Compose infrastructure."""   
    # 1. Start Database
    print("Starting Database...")
    run_command('docker-compose -p devstore up -d postgres')
    # Wait a bit to ensure containers are ready
    time.sleep(10)

    # 2. Execute an initial script in PostgreSQL
    print("Creating users and schemas...")
    execute_db_script(f"CREATE USER {catalog_db_user} WITH PASSWORD '{catalog_db_password}';")
    execute_db_script(f"CREATE SCHEMA {catalog_db_schema} AUTHORIZATION {catalog_db_user};")
    execute_db_script(f"CREATE USER {rating_db_user} WITH PASSWORD '{rating_db_password}';")
    execute_db_script(f"CREATE SCHEMA {rating_db_schema} AUTHORIZATION {rating_db_user};")
    # execute_db_script(script_create_user('catalog', 'catalog'))
    # execute_db_script(script_create_schema('catalog', 'catalog'))
    # execute_db_script(script_create_user('rating', 'rating'))
    # execute_db_script(script_create_schema('rating', 'rating'))

    # # 3. Execute Flyway in the Spring Boot services
    print("Creating initial DDL and DML ...")
    current_dir = os.path.dirname(os.path.abspath(__file__))
    three_levels_up = os.path.dirname(os.path.dirname(os.path.dirname(current_dir)))
    services_path = os.path.join(three_levels_up, 'service')
    flyway_migrate(os.path.join(services_path, "catalog/src/main/resources/db"),db_url,catalog_db_user,catalog_db_password,catalog_db_schema)
    flyway_migrate(os.path.join(services_path, "rating/src/main/resources/db"),db_url,rating_db_user,rating_db_password,rating_db_schema)

def up_services(services:list[str]):
    """Start the Docker Compose infrastructure services."""
    if not services or len(services) == 0:
        print("Starting Infrastructures Services...")
        run_command('docker-compose -p devstore up -d')
    else : 
        services_str = ' '.join(services)
        print(f"Starting Infrastructures ${services_str} Services...") 
        run_command('docker-compose -p devstore up -d ' + services_str)

def down_services(services:list[str]):
    """Stop the Docker Compose."""
    if not services or len(services) == 0:
        print("Stop services...")
        run_command('docker-compose -p devstore down')
    else :
        services_str = ' '.join(services)
        print(f"Stop services ${services_str}...")
        run_command('docker-compose -p devstore down ' + services_str) 

def main(action:str, services: list[str]):
    """Main function to execute the script."""
    try:   
        if action == 'up':
            validate_environment_variables()
            register_environment_variables()
            if not services or len(services) == 0:
                up_postgres()
                up_services([])
            else :
                if 'postgres' in services:
                    up_postgres()
                    services.remove('postgres')
                up_services(services)
        elif action == 'down':
            if not services or len(services) == 0:
                down_services([])
            else:
                down_services(services)
        else:
            print("Unrecognized action. It should be 'up' or 'down'.")
    except subprocess.CalledProcessError as e:
        print(f"An error occurred: {e}")

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='Sample script')
    parser.add_argument('action', choices=['up', 'down'], help="Action to perform ('up' or 'down')")
    parser.add_argument('services', help="Comma-separated list of services")
    args = parser.parse_args()
    main(args.action, args.services.split(','))