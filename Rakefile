require 'git'
require 'rake'

task :tests do
  sh "cd services/catalog-service && ./gradlew test"
end

task :create_version do
  # Run standard-version to bump the version and create a new tag 
  system("standard-version")
end

task :create_release do
  # Create a new release branch and push it to the remote repository 
  git = Git.open('.')  
  current_branch = git.current_branch
  git.add(:all => true)
  git.commit("chore: release #{`cat package.json | jq -r .version`}\n\n#0")
  git.add_tag("v#{`cat package.json | jq -r .version`}")
  git.push('origin', current_branch)

  release_branch_name = "release/#{current_branch}"
  git.branch(release_branch_name).checkout
  git.push('origin', release_branch_name)
  git.checkout('main')
  git.merge(release_branch_name)
  git.push('origin', 'main')
  git.checkout(current_branch)
end

task release: [:tests, :create_version, :create_release]
task default: []
