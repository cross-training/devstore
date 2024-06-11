require 'git'
require 'rake'
require 'json'

task :tests do
  sh "cd services/catalog-service && ./gradlew test"
end

task :create_version do
  # Run standard-version to bump the version and create a new tag 
  system("standard-version")
end

def get_version
  JSON.parse(File.read('package.json'))['version']
end


task :create_release do
  # Create a new release branch and push it to the remote repository 
  git = Git.open('.')  
  current_branch = git.current_branch
  version = get_version
  git.add(all: true)
  git.commit("chore: release #{version}\n\n#0")  
  git.push('origin', current_branch)


  if git.branches.local.any? { |branch| branch.name == 'release' }
    git.branch('release').checkout
  else
    git.branch('release').create
  end
  if git.branches.remote.any? { |branch| branch.name == 'origin/release' }
    git.push('origin', 'release')
  else
    git.push('origin', 'HEAD:release')
  end
  git.checkout('main')
  git.merge('release')
  git.push('origin', 'main')
  git.checkout(current_branch)
  git.branch('release').delete
  # git.push('origin', ":release")
end

task release: [:tests, :create_version, :create_release]
task default: []
