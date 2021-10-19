## Android-CICD

This repo demonstrates how to work on CI/CD for Mobile Apps :iphone: using Github Actions :pill: + Firebase Distribution :tada:

# Getting Started 

We are here setup a continious integration piplines using [**Github Actions**](https://github.com/features/actions) and a continious delivery using [**Firebase Distribution**](https://firebase.google.com/docs/app-distribution) ⚡ ⚡

If you want to know a brief definition for the two terms CI/CD 🙆‍♂️, Checkout out the quotes :

  * [Continuous Integration](https://en.wikipedia.org/wiki/Continuous_integration)

    > Is the practice of merging all developers' working copies to a shared mainline several times a day.

 * [Continuous Delivery](https://en.wikipedia.org/wiki/Continuous_delivery)

    > Is a software engineering approach in which teams produce software in short cycles, 
    > ensuring that the software can be reliably released at any time and, when releasing the software, without doing so manually.


 ![CI/CD](https://github.com/MohamedGElsharkawy/Android-CICD/blob/master/CI-CD-Image.png)


# Workflows 

  * 🚀 [pre_check.yaml](https://github.com/MohamedGElsharkawy/Android-CICD/blob/master/.github/workflows/pre_check.yml) : This workflow have to check for lint, testing and static code analyzer 
  * 🚀 [build.yaml](https://github.com/MohamedGElsharkawy/Android-CICD/blob/master/.github/workflows/build.yml) : This workflow have to build and deploy to firebase distribution

## Getting Started With CI ⚡

 To get start with build CI piplines, you should use **Actions tab** or create a new **YAML** file, then you could setup your workflow, please checkout [Metadata syntax for GitHub Actions](https://docs.github.com/en/actions/creating-actions/metadata-syntax-for-github-actions) :monocle_face:
 
 ### How to create your own workflow 

1. **name** 

> Name of your workflow 

2. **on** 

> Control when the workflow will be triggerd

3. **jobs** 

> Deterimne one or more jobs / pipelines to run for the workflow, but you have to specifiy some parameters to run the job

   * **job_name** 

   > Pick up your own job name

   * **runs_on**

   > Specify your runner type

   * **steps**

   > Represent a squence of tasks will be exceuted for each job / pipeline, each step have a some of parameters

  * **uses**

   > One of step parameters, you can use it when your are trying to install enviornment or repository from marketplace 

  * **run**

   > One of step parameters, you can use it when your are trying to hit a command 


### CI Sample :partying_face:

This workflow run as a lint checker for each **Pushing** on master branch :rocket:

```
name: Build lint checker report

on:
  push:
    branches: [ master ]

jobs:
  lint:
    runs-on: ubuntu-latest
    steps: 
      - name: Checkout
        uses: actions/checkout@v2

      - name: Setup Java JDK
        uses: actions/setup-java@v1
        with:
          java-version: 1.8

      - name: Build Lint
        run: ./gradlew lintDebug
    
      - name: Upload Build Lint Report
        uses: actions/upload-artifact@v2
        with:
          name: report
          path: app/build/reports/lint-results-debug.html
```


* [Checkout](https://github.com/marketplace/actions/checkout) : This action checks-out your repository under `$GITHUB_WORKSPACE`, so your workflow can access it.
* [Setup Java JDK](https://github.com/marketplace/actions/setup-java-jdk)
* Build Lint : Run lint report 
* [Upload Build Lint Report](https://github.com/marketplace/actions/upload-a-build-artifact) : This uploads artifacts from your workflow allowing you to share data between jobs and store data once a workflow is complete.


## Getting Started With CD ⚡

To get start with build CD piplines, you should integrate your app with **Firebase Distribution**, then you could setup your workflow, please checkout [Firebase Distribution](https://firebase.google.com/docs/app-distribution) for more how to integrate your app with firebase :monocle_face:

> Firebase App Distribution makes distributing your apps to trusted testers painless. By getting your apps onto testers' devices quickly, you can get feedback early and often. 


### CD Sample :partying_face:

This workflow builds a debug APK, then upload the artifact APK to a workflow dashboard and send another one to testers group on firebase distributions dashboard after each **Pull Request** on master branch 🚀

```
name: Integrate Firebase Distributions + Github Actions

on:
  pull_request_target:
    branches: [ master ]

jobs:
  builds:
    runs-on: ubuntu-latest
    steps: 
      - name: Checkout
        uses: actions/checkout@v2
        
      - name: Setup Java JDK
        uses: actions/setup-java@v1
        with:
         java-version: 1.8
        
      - name: Build Gradle
        run: ./gradlew build
    
      - name: Upload a Build Artifact
        uses: actions/upload-artifact@v2
        with:
          name: app
          path: app/build/outputs/apk/debug/app-debug.apk
          
      - name: Upload Artifact To Firebase App Distribution
        uses: wzieba/Firebase-Distribution-Github-Action@v1.3.2
        with:
          appId: ${{ secrets.FIREBASE_ID }}
          token: ${{ secrets.FIREBASE_TOKEN }}
          groups: Android-CICD-Testers
          releaseNotes: "Hey! This my first integrate Firebase distributions with Github Actions"
          file: app/build/outputs/apk/debug/app-debug.apk   
```

* Build Gradle : Build your APK.
* [Upload Artifact To Firebase App Distribution](https://github.com/wzieba/Firebase-Distribution-Github-Action) : This action uploads artifacts (.apk,.aab) to Firebase App Distribution.
* appId : Get it from your project settings on firebase console.
* token : Run this command `firebase login:ci`, for more informations about how to get your firbase token, check out [Firebase CLI](https://firebase.google.com/docs/cli)

> **Secrets** : This path to encrypt your sensitive information, you can access it from **Settings/Secrets Tab**, for more info checkout out [Encrypted Secrets](https://docs.github.com/en/actions/security-guides/encrypted-secrets)




 #### Screenshot from firebase distribution dashboard after sending the debug_app using Actions workflow
 ![Release](https://github.com/MohamedGElsharkawy/Android-CICD/blob/master/release_apk.png)

## Contributing 
Don't hesitate to contribute with any updates or improves, just fork this repository, make the change you'd like and then submit a pull request.

## Issues
Notice any issues with a repository? Please file a [Github Issue](https://github.com/MohamedGElsharkawy/Android-CICD/issues) in this repository.

## License

```
The MIT License (MIT)

Copyright (c) 2021 MohamedGElsharkawy

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```
