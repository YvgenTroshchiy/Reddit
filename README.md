# Simple Reddit client

Top news|News details
:-:|:-:
![](images/top_news.png)|![](images/news_details.png)

## Features
* Load top news from Reddit
* Play video on details
* Pagination
* Rotation support
* Show full image in Details Screen
* Shared element transition
* Download image
* Dark Theme
* Custom font

### WIP

### TODO
* Play gif animation in thumbnails
* Add sound to video (Reddit splits sound from video)
* Room
* Image placeholder

## Technologies, Components
* MVVM
* Kotlin, Coroutines, Flow
* Multi-module
* Android architecture components
    * ViewModel
    * <s>LiveData</s>. Was replaced by Flow.
* Navigation library
* ViewBinding
* Koin 2
* MaterialCardView
* Retrofit

## Maybe
* Pagination library

## Other
* DataBinding was removed because I strongly against about it.

## In _dagger_ branch (Obsolete and deleted)
* Android Dagger 2
* Rxjava 2
