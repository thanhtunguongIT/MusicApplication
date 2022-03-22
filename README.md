# MusicApplication

Hi folks, this is my sample project with some of the common android technologies

## FEATURES:
1. Displaying a album list returned by API calls, each item will have album's title, its singer's name and a list of thumbnails.
The data should be combined from the 3 APIs as below:
- API to get all the albums: https://jsonplaceholder.typicode.com/albums
- API to get all the singers: https://jsonplaceholder.typicode.com/users
- API to get all the photos: http://jsonplaceholder.typicode.com/photos

2. Search for albums by its singer's name/username. The list should display albums that satisfy the search key or display a message if search result is empty.
Clearing the search text will bring back all the albums.

3. Upon clicking on an item will open a screen to show all of its photos.

## TECHNICAL
### Architecture (visual diagrams from Raywenderlich)
Its Clean Architecture & MVVM design pattern in presentation layer
 ![layers-architecture-650x288](https://user-images.githubusercontent.com/46584969/159474458-e2ab12d0-d7a3-4f20-aa05-1fd002e94ca9.png)
 ![MVVM-in-Clean-Architecture-650x271](https://user-images.githubusercontent.com/46584969/159475381-f076186c-a696-4648-aff9-04360199bec3.png)
### Framework & Libraries
1. Build: Gradle Kotlin DSL
2. DI: Dagger 2
3. Data list: Paging 3
4. Network calls: Retrofit, Gson
5. Image loader: Glide, SquircleView
6. Unit test: Junit, Mockito, MockWebServer, KotlinCoroutinesTest

## Video
https://user-images.githubusercontent.com/46584969/159477334-0ebcbebf-d7aa-4ae5-8b0c-bff3f0dc5a47.mov


