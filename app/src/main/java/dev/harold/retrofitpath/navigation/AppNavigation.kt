package dev.harold.retrofitpath.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.harold.retrofitpath.ui.task_add.AddTaskScreen
import dev.harold.retrofitpath.ui.tasks_list.TasksListScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.TASKS_LIST.name,
    ) {
        composable(
            route = AppScreens.TASKS_LIST.name,
        ) {
            TasksListScreen(

                onAddButtonClick = {
                    navController.navigate(AppScreens.ADD_TASK.name)
                },
            )
        }

        composable(
            route = AppScreens.ADD_TASK.name,
        ) {
            AddTaskScreen(
                onCancelButtonClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}

enum class AppScreens {
    TASKS_LIST,
    ADD_TASK,
}
