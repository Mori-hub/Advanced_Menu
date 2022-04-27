
# Advanced Menus 

This is kind of a Menu includes Animations .
## Features

- Menus: UpperMenu, CubeMenu, FalldownMenu
- Easy Set up
- Items setup : Icons, colors, text  
- Free Position : Put anywhere in Parent Layout
- Get Running Function :Repeatdly to do
- Animations: Enter and Exit
- Colors : Changing colors from / to 


## 📚Installation

Install my-project with https://jitpack.io/

```bash
  dependencies {
	        implementation 'com.github.Mori-hub:'
	}
```
    
## 🧰Usage

```javascript
    // Call Lib UpperMenu
    val upper = UpperMenu(this, null, 0, Parent layout)
    // Call Lib CubeMenu
	 val cubeMenu = CubeMenu(this, null, 0,Parent layout)
	// Call Lib FalldownMenu
	 val fallDownMenu = FallDownMenu(this, null, 0, Parent layout)	
        
```
## Explain
* **Parent Layout** : This must be a Layout and put anywhere
* **null** : For Running Function and Finish Function
* **Show** : When you call UpperMenu method, the layout will attach to the window 
* **Show** : For Cube and Falldown use .show() 
* **SetItems** : For set as you need
## Structure
```javascript
setItems(
        backToolbar: Int,
        icons: ArrayList<Int>,
        fromColors: ArrayList<Int>,
        toColors: ArrayList<Int>,
        text: ArrayList<String>,
        runningFunctions: ArrayList<(() -> Unit)?>
    )
```   
## Examples
```javascript
val upper = UpperMenu(this, null, 0, R.id.mainParent)
     upper.setItems(
            Color.parseColor("#FCF69C"),
            arrayListOf(
                android.R.drawable.ic_btn_speak_now,
                android.R.drawable.ic_delete, android.R.drawable.ic_input_add,
                android.R.drawable.ic_media_play
            ),
            arrayListOf(
                Color.parseColor("white"),
                Color.parseColor("#55D8C1"),
                Color.parseColor("#FF6FB5"),
                Color.parseColor("#97D0AE")
            ),
            arrayListOf(
                Color.parseColor("gray"),
                Color.parseColor("#F55353"),
                Color.parseColor("#FEB139"),
                Color.parseColor("#F56D91")
            ),
            arrayListOf("Recording", "Delete", "Add", "Play"), arrayListOf(
                null,
                {
                    Toast.makeText(applicationContext, "Hi", Toast.LENGTH_SHORT).show()
                    println("Hi there!")
                },
                { fallDownMenu.show() },
                { cubeMenu.show() }
            )
        ) 
```

## Optinal logoToolbar
 If you want a logo on your toolbar call this method
 ## Structure
```javascript
logoToolbar(
	logo: Int, 
	backColor: Int? = null, 
	click: (() -> Unit)?
)
```   
## Examples
```javascript
    upper.logoToolbar(android.R.drawable.sym_contact_card,null) {
            Toast.makeText(applicationContext, "Hi", Toast.LENGTH_SHORT).show()
        }
```
## Optinal toolbar
If you are going to use it as a toolbar , do like this:
- setTheme as a NoActionBar
- put it before setContentView
- add True for last parameter of UpperMenu

## Examples
```javascript
     this.setTheme(androidx.appcompat.R.style.Theme_AppCompat_DayNight_NoActionBar)
        setContentView(R.layout.activity_main)
	 val upper = UpperMenu(this, null, 0, R.id.mainParent, true)
```

## Screenshots

<img src="https://user-images.githubusercontent.com/53067774/165560290-9d7903b2-37fa-4be5-a56f-16e7cadec9c0.jpg" width="15%">
</img> <img src="https://user-images.githubusercontent.com/53067774/165560304-a6a81afe-a835-4c61-b09b-69b0f1c2dd3d.jpg" width="15%">
</img> <img src="https://user-images.githubusercontent.com/53067774/165560315-4ec1e1da-2076-4d69-9189-b755116b144e.jpg" width="15%">
</img> <img src="https://user-images.githubusercontent.com/53067774/165560327-a9321ab0-8c19-4874-be40-da6f9b3523bd.jpg" width="15%"></img> 

## Gifs
![see](https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/5eeea355389655.59822ff824b72.gif)

<img src="./assets/image.gif" width="50%" height="50%"/>

## More Methods
As you can see in the images, there are two more menus and you can call them.
## Structure FallDownMenu
```javascript
     fun setItems(
        backToolbar: Int? = null,
        icons: ArrayList<Int>,
        textIcons: ArrayList<String>,
        backColors: ArrayList<Int>,
        runningFunctions: ArrayList<(() -> Unit)?>
    ) 
```
## Structure CubeMenu
```javascript
      fun setItems(
        backToolbar: Int,
        icons: ArrayList<Int>,
        fromOneColor: Int,
        toOneColor: Int,
        runningFunctions: ArrayList<(() -> Unit)?>
    ) 
```

## 🖐Important Notic
Please add parameters due to number of icons, otherwise the app will crash and close.

## 🚀 About Me
I'm a full stack developer...


## 🛠 Skills
Java, Kotlin, CSS....


## Tech Stack

**Important:** For Gradle 7.2 & jitpack.io Please use this way : https://stackoverflow.com/a/71603699/12272687

**Update:** March 2022


## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/Mori-hub)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/)
[![twitter](https://img.shields.io/badge/twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/)
[![Google](https://img.shields.io/badge/My%20Apps-Google%20Play%20Store-green?style=for-the-badge&logo=googleplay)](https://play.google.com/store/search?q=pub:Smart%20rabit&c=apps)

## Feedback

If you have any feedback, please reach out to us at SR-App@outlook.com


## License

[MIT](https://choosealicense.com/licenses/mit/)

