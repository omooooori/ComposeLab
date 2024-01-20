package com.omooooori.composablelab.ui.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LearningEnglishUI() {
    val allWords = remember { mutableStateListOf("I", "am", "learning", "English", "with", "Android") }
    val selectedWords = remember { mutableStateListOf<String>() }

    Column {
        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
            items(selectedWords.size) { index ->
                Text(
                    text = selectedWords[index],
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text("Tap on a word to add it to the sentence.")

        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
            items(allWords.size) { index ->
                val word = allWords[index]
                Button(
                    onClick = {
                        allWords.remove(word)
                        selectedWords.add(word)
                    }
                ) {
                    Text(text = word)
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun LearningEnglishUI2() {
    val allWords = remember { mutableStateListOf("I", "am", "learning", "English", "with", "Android") }
    val selectedWords = remember { mutableStateListOf<String>() }
    val selectedIndex = remember { mutableStateListOf<Int>() }

    Column {
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(allWords.size) { index ->
                AnimatedVisibility(
                    visible = !selectedIndex.contains(index),
                    enter = expandHorizontally() + fadeIn(),
                    exit = shrinkHorizontally() + fadeOut()
                ) {
                    Button(
                        onClick = {
                            selectedIndex.add(index)
                            selectedWords.add(allWords[index])
                        }
                    ) {
                        Text(allWords[index])
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }

        Spacer(Modifier.width(16.dp))

        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(selectedWords.size) { index ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.width(86.dp)
                ) {
                    Text(selectedWords[index])
                }
            }
        }
    }
}

@Composable
fun LearningEnglishUI3() {
    val availableWords = remember { mutableStateListOf("I", "am", "learning", "English", "with", "Android") }
    var selectedWords by remember { mutableStateOf(listOf<String>()) }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val screenHeight = maxHeight

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight / 2)
                .align(Alignment.TopStart)
                .background(Color.LightGray)
        ) {
            SentenceBuilder(
                words = selectedWords,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight / 2)
                .align(Alignment.BottomStart)
                .background(Color.White)
        ) {
            WordsSelection(
                words = availableWords,
                modifier = Modifier.align(Alignment.Center),
                onWordSelected = { word ->
                    availableWords.remove(word)
                    selectedWords = selectedWords + word
                }
            )
        }
    }
}

@Composable
fun WordsSelection(
    words: List<String>,
    modifier: Modifier = Modifier,
    onWordSelected: (String) -> Unit
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(words.size) { index ->
            val word = words[index]
            Button(
                onClick = { onWordSelected(word) }
            ) {
                Text(word)
            }
        }
    }
}

@Composable
fun SentenceBuilder(
    words: List<String>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(words.size) { index ->
            Text(words[index])
        }
    }
    Text(
        text = "Hello World"
    )
}

@Preview(showBackground = true)
@Composable
fun LearningEnglishUIPreview() {
    LearningEnglishUI3()
}
