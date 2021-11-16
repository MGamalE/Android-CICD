## How to integrate sonar cloud with your project

Before starting the integratation, you have login with your account on [sonarcloud](https://sonarcloud.io/sessions/new)

* After login to your account, you will see importing an organization option, so click `importing an organization from github` 
<p align="start">
<img src="https://github.com/MohamedGElsharkawy/Android-CICD/blob/master/screenshots/import-org.png" width="800" height="500">

</p>


* After that, select your `organzaiton` which will your are integrate with it.
<p align="start"> 
<img src="https://github.com/MohamedGElsharkawy/Android-CICD/blob/master/screenshots/choose-org.png" width="800" height="500">	
</p>


* You will be navigated to your github to completed installing and access for needed repository, 
you select `only selected repositories` option, then determine your repository and click save 

<p align="start">
<img src="https://github.com/MohamedGElsharkawy/Android-CICD/blob/master/screenshots/choose-repo.png" width="800" height="500">	
</p>

* :rocket: Greate!, now back to your sonarcloud account and Select `analyze new project`

<p align="start">
<img src="https://github.com/MohamedGElsharkawy/Android-CICD/blob/master/screenshots/new-project.png" width="800" height="500">	
</p>
	- If you have imported project before, and want to add a new one, from top header select + then `analyze new project`
	
<p align="start">
<img src="https://github.com/MohamedGElsharkawy/Android-CICD/blob/master/screenshots/new-project2.png" width="800" height="500">	
</p>


* Choose your repository, then click `setup` 
<p align="start">
<img src="https://github.com/MohamedGElsharkawy/Android-CICD/blob/master/screenshots/import-repo.png" width="800" height="500">	
</p>


* Congrats :tada:, you have completed your setup, so now you have to disable your `Automatic analysis` from `project settings /Analysis method`, toggle `sonarcloud automatic analysis` to `off`
	- `**Important: if you not disabled the automatic anaylsis optino your pipeline will not work**`
<p align="start">
<img src="https://github.com/MohamedGElsharkawy/Android-CICD/blob/master/screenshots/disable.png" width="800" height="500">	
</p>	

* From project settings select  `anlyasis method`, then click `follow the toturial for github action`
<p align="start">
<img src="https://github.com/MohamedGElsharkawy/Android-CICD/blob/master/screenshots/config.png" width="800" height="500">	
</p>	

* You will be navigated to complete integrate steps, first step copy the generated secret token, then stoe it in you `github repository/settings/secrets` with key name `SONAR_TOKEN`
<p align="start">
<img src="https://github.com/MohamedGElsharkawy/Android-CICD/blob/master/screenshots/gen-secret.png" width="800" height="500">	
</p>	

* Second step copy your dependency into your android studio project, then sync your project 
<p align="start">
<img src="https://github.com/MohamedGElsharkawy/Android-CICD/blob/master/screenshots/update-dep.png" width="800" height="500">	
</p>	


* Back to your github repository, and add this action for sonarlcoud job in your workflow
```
- name: Upload Code Anaylysis To SonarCloud
        run: ./gradlew build sonarqube --info
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
          SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
```

* Congrats :tada: your project is ready :rocket: to run the pipeline 

<p align="start">
<img src="https://github.com/MohamedGElsharkawy/Android-CICD/blob/master/screenshots/sonar-result.png" width="800" height="500">	
</p>



