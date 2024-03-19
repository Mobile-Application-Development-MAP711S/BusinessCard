package com.example.buildup

import android.content.ContentValues.TAG
import android.graphics.fonts.FontStyle
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buildup.ui.theme.BuildUpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuildUpTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    // invoking the BusinessCard function in the main function
                    BusinessCard()

                }
            }
        }
    }
}


@Composable
fun BusinessCard(){

    // variable to store button state (false or true)
    var buttonCLickedState = remember {
        mutableStateOf(false)
    }

    Surface(modifier = Modifier.fillMaxSize()) {

        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {

            Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {

                // invoking the other functions below
                ProfileImage()
                Divider()
                ProfileInfo()

                Button(onClick = {

                    // whenever the button is clicked it will change the value
                    // to the opposite value if true then it will be false or vice versa
                    buttonCLickedState.value = !buttonCLickedState.value
                },

                    modifier = Modifier.padding(top = 20.dp)

                    ) {

                    Text(text = "Portfolio")

                }

                // if button state is true
                if (buttonCLickedState.value){
                    // invoke the below funciton
                    PortfolioContent()
                } else {
                    // otherwise just display an empty box
                    Box() {

                    }
                }


            }


        }

    }
}


@Composable
fun ProfileImage(modifier: Modifier = Modifier ){
    Surface(modifier = modifier
        .size(150.dp)
        .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        ) {

        Image(painter = painterResource(id = R.drawable.profile_image), contentDescription = "profile image",
        modifier = modifier.size(135.dp),
        contentScale = ContentScale.Crop)
    }
}


@Composable
fun ProfileInfo() {
    Column(modifier = Modifier
        .padding(top = 20.dp)) {

        Text(text = "Zinha T.",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary)

        Text(text = "Android Compose Programmer",
            style = MaterialTheme.typography.titleMedium)

        Text(text = "@ZinhaCompose",
            style = MaterialTheme.typography.titleMedium)

    }
}


// passing in a list as a parameter
@Composable
fun Portfolio(data: List<String>) {
    
    LazyColumn {

        // iterate through the list
        items(data) {


            // list item at iteration
            item->
            
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
            shape = RectangleShape,
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            colors = CardDefaults.cardColors(contentColor = Color.Black)) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp)
                    .background(MaterialTheme.colorScheme.onPrimary)
            ) {

                ProfileImage(modifier = Modifier.size(100.dp))

                Column(modifier = Modifier.padding(7.dp).align(alignment = Alignment.CenterVertically)) {



                    // take the value of the current iteration and make it as the text of the Text
                    Text(text = item, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)


                    Text(text = "A great project", style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray
                    )

                }

            }

        }
            
            
            
        }
    
    }
    
}

@Composable
fun PortfolioContent(){
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp)
        .padding(20.dp)) {
        
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(3.dp),
            color = Color.White,
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(2.dp, Color.LightGray)
        ) {
            
            Column(modifier = Modifier.fillMaxSize()) {

                // invoke the Portfolio function with a list passed in
                Portfolio(data = listOf("Project 1", "Project 2", "Project 3", "Project 4", "Project 5",
                    "Project 6", "Project 7", "Project 8", "Project 9", "Project 10"))
                
            }
            
        }
        
    }
}


@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    BuildUpTheme {
        BusinessCard()

    }
}

