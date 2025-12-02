@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.quicknotesplus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quicknotesplus.ui.add_list.AddEditNoteScreen
import com.example.quicknotesplus.ui.navigation.Routes
import com.example.quicknotesplus.ui.note_list.NoteListScreen
import com.example.quicknotesplus.ui.theme.QuickNotesPlusTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuickNotesPlusTheme {
                QuickNotesApp()
            }
        }
    }
}

@Composable
fun QuickNotesApp(
    navController: NavHostController = rememberNavController()
) {
    Scaffold {

        NavHost(
            navController = navController,
            startDestination = Routes.NOTES_LIST
        ) {
            composable(route = Routes.NOTES_LIST) {
                NoteListScreen(
                    onClick = { id ->
                        navController.navigate("${Routes.ADD_EDIT_NOTE}/$id")
                    },
                )
            }

            composable(
                route = "${Routes.ADD_EDIT_NOTE}/{id}",
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType }
                )) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id") ?: -1
                AddEditNoteScreen(
                    noteId = id,
                    onSaveClick = {
                        navController.navigateUp()
                    },
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

@Composable
@PreviewLightDark
fun PreviewNotesApp() {
    QuickNotesPlusTheme {
        QuickNotesApp()
    }
}