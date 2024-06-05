package com.example.alkye

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alkye.ui.theme.AlkyeTheme
import com.example.alkye.ui.theme.Strawford
import com.example.alkye.ui.theme.firaSansFamily
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlkyeTheme {
                var showSplashScreen by remember { mutableStateOf(true) }

                LaunchedEffect(Unit) {
                    delay(1500)
                    showSplashScreen = false
                }

                if (showSplashScreen) {
                    SplashScreen()
                } else {
                    MainScreen2()
                }
            }
        }
    }
}
@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Centered Logo
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), // Replace with your logo resource id
                contentDescription = "Logo",
                modifier = Modifier.size(120.dp)
            )
        }

        // Profile Picture and Name at the bottom right
        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 150.dp),
        ) {

            Text(
                text = "Aneesh Bond", // Replace with the name
                fontSize = 30.sp,
                fontFamily = firaSansFamily,
                color = Color.Black,
                modifier = Modifier.padding(end = 30.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.img), // Replace with your profile image URL
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .padding(start = 0.dp)
                    .clip(CircleShape)
                    .size(90.dp)
            )
        }

    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen2() {
    val navController = rememberNavController()
    val selectedItem = remember { mutableStateOf<NavigationItem>(NavigationItem.MainScreen1) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, selectedItem = selectedItem)
        }
    ) {
        // Navigation content
        Navigation(navController = navController)
    }
}

@Composable
fun MainScreen1() {
    Column(modifier = Modifier
        .background(Color.LightGray).padding(top = 20.dp)
        .verticalScroll(rememberScrollState())
    ){

        Column(modifier = Modifier) {
            TopBar()
            crausal()
            Text(
                text = "Recent Articles",
                modifier = Modifier.padding(horizontal = 17.dp, vertical = 12.dp),
                fontSize = 20.sp,
                fontFamily = Strawford,
                fontWeight = FontWeight.ExtraBold
            )
            VerticalScrollSection()
        }


        Column(modifier = Modifier
            .background(Black)
            .padding(bottom = 94.dp)){
            SocialConnectSection()
        }

    }
}



@Composable
fun BottomNavigationBar(navController: NavHostController, selectedItem: MutableState<NavigationItem>) {



    BottomNavigation(
        backgroundColor = White,
        contentColor = Black,
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(45.dp))

    ) {
        val items = listOf(
            NavigationItem.MainScreen1,
            NavigationItem.Bookmark,
            NavigationItem.Tv,
            NavigationItem.Notifications,
            NavigationItem.Person
        )
        items.forEach { item ->
            BottomNavigationItem(modifier = Modifier.height(70.dp),
                icon = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(CircleShape)
                            .background(
                                if (item == selectedItem.value) Black else Transparent
                            ) // Circle background color based on selection
                            .padding(8.dp)// Adjust padding as needed
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = if (item == selectedItem.value) White else Black,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                selected = item == selectedItem.value,
                selectedContentColor = White, // Color for icon and text when selected
                unselectedContentColor = Black, // Color for icon and text when unselected
                onClick = { selectedItem.value = item
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                alwaysShowLabel = false
            )
        }
    }
}


sealed class NavigationItem(var route: String, var icon: ImageVector, var title: String) {
    object MainScreen1 : NavigationItem("main_screen_1", Icons.Filled.Home, "Home")
    object Bookmark : NavigationItem("bookmark", Icons.Filled.Search, "Bookmark")
    object Tv : NavigationItem("tv", Icons.Filled.Edit, "TV")
    object Notifications : NavigationItem("notifications", Icons.Filled.Notifications, "Notifications")
    object Person : NavigationItem("person", Icons.Filled.Person, "Person")
}
@Composable
fun Navigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = NavigationItem.MainScreen1.route, modifier = modifier) {
        composable(NavigationItem.MainScreen1.route) { MainScreen1() }
        composable(NavigationItem.Bookmark.route) { BookmarkScreen() }
        composable(NavigationItem.Tv.route) { TvScreen() }
        composable(NavigationItem.Notifications.route) { NotificationsScreen() }
        composable(NavigationItem.Person.route) { PersonScreen() }
    }
}

@Composable
fun BookmarkScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Search")
    }
}

@Composable
fun TvScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Edit")
    }
}

@Composable
fun NotificationsScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Notifications Screen")
    }
}

@Composable
fun PersonScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Person Screen")
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    var searchText by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(65.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            },
            singleLine = true,
            placeholder = {
                Text("Search ....", color = Gray, fontSize = 12.sp)
            },
            textStyle = TextStyle(fontSize = 14.sp),
            modifier = Modifier
                .height(49.dp)
                .width(160.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = White,
                cursorColor = Black,
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent,
            ),
            shape = RoundedCornerShape(30.dp)
        )
    }
}


@Composable
fun HorizontalScrollSection() {
    LazyRow(modifier = Modifier.background(Black)) {
        items(5) {
            ArticleCard()
        }
    }
}
@Composable
fun crausal() {
    LazyRow(modifier = Modifier.padding(16.dp)) {
        items(5) {
            ArticleCard1()
        }
    }
}
@Composable
fun ArticleCard1() {
    Box( modifier = Modifier
        .width(260.dp)
    ) {
        Card(
            modifier = Modifier.padding(end = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = White
            )

        ) {
            Image(
                painter = painterResource(id = R.drawable.car), // Replace with your article image URL
                contentDescription = "Article Image",
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillHeight
            )

            Row (modifier = Modifier.padding(start = 16.dp, top = 18.dp)){
                Image(
                    painter = painterResource(id = R.drawable.dot),
                    contentDescription = "Car Image",
                    modifier = Modifier
                        .size(18.dp),

                    )
                Text(text = "technology", fontSize = 12.sp, modifier = Modifier.padding(start = 8.dp), fontFamily = Strawford, fontWeight = FontWeight.SemiBold)
            }
            Text(
                text = "The Ultimate Guide To Simplifying Your Routine With Generative AI Automation!", // Replace with actual article title
                fontFamily = Strawford,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Black,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.insta),
            contentDescription = "Car Image",
            modifier = Modifier
                .padding(start = 18.dp, top = 18.dp)
                .size(38.dp)
                .clip(RoundedCornerShape(7.dp))
        )
        Image(
            painter = painterResource(id = R.drawable.star),
            contentDescription = "Car Image",
            modifier = Modifier
                .padding(end = 38.dp, top = 18.dp)
                .size(38.dp)
                .align(Alignment.TopEnd)
                .clip(RoundedCornerShape(7.dp))
        )
    }

}

@Composable
fun VerticalScrollSection() {
    var expanded by remember { mutableStateOf(false) }
    val itemsToShow = if (expanded) 14 else 3
    LazyColumn(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .height(870.dp)){
        items(itemsToShow) {
            StepIntoTomorrowCard()
        }
        item {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { expanded = !expanded },
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .width(180.dp)
                        .height(50.dp)
                        .clip(RoundedCornerShape(30.dp)),
                    colors = ButtonDefaults.buttonColors(Color.White)
                ) {
                    Text(
                        text = if (expanded) "Show Less" else "View All",
                        fontFamily = Strawford,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
//@Composable
//fun button(){
//    Button(
//        onClick = { },
//        modifier = Modifier
//            .padding(vertical = 8.dp).width(180.dp).height(50.dp)
//            .clip(RoundedCornerShape(30.dp)),
//        colors = ButtonDefaults.buttonColors(Color.Red)
//
//    ) {
//        Text(text =  "View All")
//    }
//}
@Composable
fun ArticleCard() {
    Box( modifier = Modifier
        .width(230.dp)
        .background(Black)) {
        Card(
            modifier = Modifier.padding(end = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = White
            )

        ) {
            Image(
                painter = painterResource(id = R.drawable.car), // Replace with your article image URL
                contentDescription = "Article Image",
                modifier = Modifier
//                .fillMaxHeight()
                    .fillMaxWidth(),
                contentScale = ContentScale.FillHeight
            )

            Text(
                text = "The Ultimate Guide To Simplifying Your Routine With Generative AI Automation!", // Replace with actual article title
                fontFamily = Strawford,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Black,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.insta),
            contentDescription = "Car Image",
            modifier = Modifier
                .padding(start = 18.dp, top = 18.dp)
                .size(38.dp)
                .clip(RoundedCornerShape(7.dp))
        )
    }

}
@Composable
fun SocialConnectSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Social Connect",
            fontFamily = Strawford,
            fontSize = 27.sp,
            fontWeight = FontWeight.ExtraBold,
            color = White,
        )
        Text(text = "Stay update with my latest post\non your favorite platforms",
            fontFamily = Strawford,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = White,
            modifier = Modifier.padding(bottom = 15.dp)
        )

        HorizontalScrollSection()

    }
}

@Composable
fun StepIntoTomorrowCard() {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
            .height(250.dp),
        colors = CardDefaults.cardColors(
            containerColor = White
        )
    ) {
        Row{

            Image(
                painter = painterResource(id = R.drawable.car),
                contentDescription = "Car Image",
                modifier = Modifier
                    .width(160.dp)
                    .fillMaxHeight(),
                contentScale = ContentScale.FillHeight
            )
            Column(
                modifier = Modifier
                    .padding(14.dp)
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_1),
                    contentDescription = "Car Image",
                    modifier = Modifier
                        .padding(bottom = 15.dp)
                        .size(38.dp)
                )
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.dot),
                        contentDescription = "Car Image",
                        modifier = Modifier
                            .size(18.dp),

                        )
                    Text(text = "technology", fontSize = 12.sp, modifier = Modifier.padding(start = 8.dp), fontFamily = Strawford, fontWeight = FontWeight.Bold)
                }
                Text(
                    text = "Step Into Tomorrow:",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = Strawford,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 15.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Exploring Spatial Computing’s Impact On Industries And The Metaverse!",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = Strawford,
                        fontSize = 16.sp
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "26 Feb 2024",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontFamily = Strawford,
                        color = Gray,
                        fontSize = 12.sp
                    ),


                    )
            }

        }

    }

}
@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    AlkyeTheme {
//        MainScreen1()
        TopBar()
//        HorizontalScrollSection()
//        VerticalScrollSection()
//        SocialConnectSection()
//        BottomNavigationBar()
//        StepIntoTomorrowCard()
//        ArticleCard()
//        crausal()
//        button()
    }
}