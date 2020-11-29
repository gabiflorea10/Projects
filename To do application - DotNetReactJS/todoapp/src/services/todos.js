import { ActionCreators } from "../redux/todosReducer"
import * as axios from 'axios'


const axiosInstance = axios.create({
    baseURL: 'https://localhost:5001/todos'
})


export const GetTodos = async (dispatch) => {
    try {
        // api call
        const { data } = await axiosInstance.get();

        dispatch(ActionCreators.setTodos(data))

    }
    catch {
        console.log("Error!")
    }


}
export const NewTodo = async (dispatch, todo) => {
    try {
        // api call
        const { data } = await axiosInstance.post('', todo)
        dispatch(ActionCreators.newTodo(data))

    }
    catch {
        console.log("Error!")
    }
}

export const EditTodo = async (dispatch, todo) => {
    try {
        // api call
        await axiosInstance.put('', todo);
        dispatch(ActionCreators.editTodo(todo))

    }
    catch {
        console.log("Error!")
    }

}
export const DeleteTodo = async (dispatch, todo) => {
    try {
        // api call
       await axiosInstance.delete(`/${todo.id}`)
        dispatch(ActionCreators.deleteTodo(todo))

    }
    catch {
        console.log("Error!")
    }


} 