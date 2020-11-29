import { act } from "react-dom/test-utils"

const initialState = {
    todos: [],
}

export const ActionTypes = {
    SET_TODOS: 'SET_TODOS',
    DELETE_TODO: 'DELETE_TODO',
    NEW_TODO: 'NEW_TODO',
    EDIT_TODO: 'EDIT_TODO'
}

export const ActionCreators = {
    setTodos: payload => ({ type: ActionTypes.SET_TODOS, payload }),
    deleteTodo: payload => ({ type: ActionTypes.DELETE_TODO, payload }),
    newTodo: payload => ({ type: ActionTypes.NEW_TODO, payload }),
    editTodo: payload => ({ type: ActionTypes.EDIT_TODO, payload }),
}

export default (state = initialState, action) => {
    switch (action.type) {
        case ActionTypes.SET_TODOS:
            return { ...state, todos: [...action.payload] }
        case ActionTypes.DELETE_TODO:
            return {...state, todos: [...state.todos.filter(todo => todo.id != action.payload.id)]}
        case ActionTypes.EDIT_TODO:   
            let todos = state.todos.map(todo =>{
                if(todo.id===action.payload.id){
                    todo = action.payload
                }
                return todo;
            })
            return {...state, todos: [...todos]}
        case ActionTypes.NEW_TODO:
            return {...state, todos: [...state.todos, action.payload]} 
        
        default:
            return state;
    }
}