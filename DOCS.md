# JENKINS-SDK DOCUMENTATION
[![Build Status](https://travis-ci.org/aistomin/jenkins-sdk.svg?branch=master)](https://travis-ci.org/aistomin/jenkins-sdk)
[![Dependencies](https://www.versioneye.com/user/projects/56b7e8d2f6e506003159ac3c/badge.svg?style=flat)](https://www.versioneye.com/user/projects/56b7e8d2f6e506003159ac3c)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.aistomin/jenkins-sdk/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.aistomin/jenkins-sdk)

## Table of Contents
* [Jenkins](#jenkins)  
* [Jenkins Users](#jenkins-users)  
    * [Iterate through Users](#iterate-through-users)
    * [Users Search](#users-search) 
    * [Read User Details](#read-user-details)
* [Jenkins Jobs](#jenkins-jobs)  
    * [Iterate through Jobs](#iterate-through-jobs)
    * [Jobs Search](#jobs-search) 
    * [Read Job Details](#read-job-details)
* [Jenkins Builds](#jenkins-builds)  
    * [Iterate through Builds](#iterate-through-builds)
    * [Builds Search](#builds-search) 
    * [Read Build Details](#read-build-details) 
    * [Trigger new Build](#trigger-new-build) 
    * [Cancel Build](#cancel-build)

## Jenkins
To create Jenkins object you should use the following code:
```
Jenkins jenkins = new RealJenkins(
    "<YOUR JENKINS URL>",
    new UsernamePasswordCredentials("<USERNAME>", "<PASSWORD>")
);
```
Just replace placeholders with real data.
After Jenkins object was created you can use it for getting information. For
example, you can get Jenkins' verson: ```jenkins.version()```.
Keep in mind that `<USERNAME>` and `<PASSWORD>` is credential of real user
which is registered in Jenkins instance `<YOUR JENKINS URL>`. Obviously, this
user has to have permission for particular action you try to do with our objects.
For example, if you try to use `Job.trigger()` method this user has to have `Run`
permission in your Jenkins configuration.

## Jenkins Users 
We provide ```RealUsers``` and ```RealUser``` classes to read information about
Jenkins users.

### Iterate through Users
Using ```jenkins``` instance of class ```RealJenkins``` you may iterate through
users:
```
Users users = jenkins.users();
System.out.println("----------------- USERS -----------------");
Iterator<User> iterator = users.iterator();
while (iterator.hasNext()) {
    User user =  iterator.next();
    System.out.println(user.fullName());
}
System.out.println("----------------------------------------");
```

### Users Search
Using ```jenkins``` instance of class ```RealJenkins``` you may search for
users:
```
Iterator<User> found = jenkins.users()
    .findByUsername("<USERNAME>");
System.out.println("----------------- FOUND USERS -----------------");
while (found.hasNext()) {
    User user =  found.next();
    System.out.println(user.fullName());
}
System.out.println("----------------------------------------------");
```
Currently we have three methods that allow to search for users:
`Users.findByUsername()`, `Users.findByEmail()` and `Users.findByFullName()`

### Read User Details
Using ```jenkins``` instance of class ```RealJenkins``` you may read particular
user details:
```
Iterator<User> users = jenkins.users().iterator();
while (users.hasNext()) {
    User user = users.next();
    System.out.println("user.fullName() = " + user.fullName());
    System.out.println("user.username() = " + user.username());
    System.out.println("user.email() = " + user.email());
    System.out.println("user.url() = " + user.url());
    System.out.println("user.description() = " + user.description());
}
```

## Jenkins Jobs 
We provide ```RealJobs``` and ```RealJob``` classes to read information about
Jenkins jobs.

### Iterate through Jobs
Using ```jenkins``` instance of class ```RealJenkins``` you may iterate through
jobs:
```
Jobs jobs = jenkins.jobs();
System.out.println("----------------- JOBS -----------------");
Iterator<Job> iterator = jobs.iterator();
while (iterator.hasNext()) {
    Job job =  iterator.next();
    System.out.println(job.name());
}
System.out.println("----------------------------------------");
```

### Jobs Search
Using ```jenkins``` instance of class ```RealJenkins``` you may search for
job:
```
Iterator<Job> found = jenkins.jobs().findByName("<JOB TO SEARCH>");
System.out.println("----------------- FOUND JOBS -----------------");
while (found.hasNext()) {
    Job job =  found.next();
    System.out.println(job.name());
}
System.out.println("----------------------------------------------");
```

### Read Job Details
Using ```jenkins``` instance of class ```RealJenkins``` you may read particular
job details:
```
Iterator<Job> jobs = jenkins.jobs().iterator();
while (jobs.hasNext()) {
    System.out.println("job.details().displayName() = " + job.details().displayName());
    System.out.println("job.details().description() = " + job.details().description());
    System.out.println("job.details().buildable() = " + job.details().buildable());
    System.out.println("job.url() = " + job.url());
}
```

## Jenkins Builds 
We provide ```RealBuilds``` and ```RealBuild``` classes to read information about
Jenkins job's builds.

### Iterate through Builds
Using ```jenkins``` instance of class ```RealJenkins``` you may iterate through
builds:
```
Job job = jenkins.jobs().findByName("<JOB NAME>").next();
System.out.println("----------------- BUILDS -----------------");
Iterator<Build> iterator = job.builds().iterator();
while (iterator.hasNext()) {
    Build build =  iterator.next();
    System.out.println(build.number());
}
System.out.println("----------------------------------------");
```

### Builds Search
Using ```jenkins``` instance of class ```RealJenkins``` you may search for
job's builds:
```
Iterator<Build> found = jenkins.jobs().findByName("<JOB NAME>")
    .next().builds().findByNumber("<BUILD NUMBER>");
System.out.println("----------------- FOUND BUILDS -----------------");
while (found.hasNext()) {
    Build build =  found.next();
    System.out.println(build.details().fullDisplayName());
}
System.out.println("----------------------------------------------");
```

### Read Build Details
Using ```jenkins``` instance of class ```RealJenkins``` you may read particular
job's build details:
```
Job job = jenkins.jobs().findByName("<JOB NAME>").next();
Iterator<Build> builds = job.builds().iterator();
while (builds.hasNext()) {
    Build build = builds.next();
    System.out.println(
        "build.details().fullDisplayName() = "
            + build.details().fullDisplayName()
    );
    System.out.println(
        "build.details().displayName() = "
            + build.details().displayName()
    );
    System.out.println(
        "build.details().duration() = " + build.details().duration()
    );
    System.out.println(
        "build.result().name() = " + build.result().name()
    );
}
```

### Trigger new Build
Using ```jenkins``` instance of class ```RealJenkins``` you may trigger new 
build:
```
Job job = jenkins.jobs().findByName("<JOB NAME>").next();
job.trigger();
// look into your Jenkins web: new build must appear.
```

### Cancel Build
Using ```jenkins``` instance of class ```RealJenkins``` you may cancel running 
build:
```
Job job = jenkins.jobs().findByName("<JOB NAME>").next();
job.trigger();
Thread.sleep(10000); // wait 10 sec until build starts.
job.builds().lastUnsuccessful().cancel();
// look into your Jenkins web: cancelled build must be there.
```