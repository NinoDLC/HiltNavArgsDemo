# HiltNavArgsDemo
From [**Navigation v2.4**](https://developer.android.com/jetpack/androidx/releases/navigation#2.4.0-alpha01) onward, a new function emerges from the generated `NavArgs` : `fromSavedStateHandle()`. 
This means `NavArgs` can be recreated not only from a `Bundle`, but also from a `SavedStateHandle` !  
Let's explore this ! 

[TL;DR here for those familiar with Hilt's SavedStateHandle](https://github.com/NinoDLC/HiltNavArgsDemo/blob/master/app/src/main/java/fr/delcey/hiltnavargsdemo/detail/DetailViewModel.kt#L20)
