import React, { useEffect } from 'react'
import { useSelector, useDispatch } from 'react-redux'
import { DeleteTodo, GetTodos } from '../services/todos'
import { Button } from 'react-bootstrap'
import {EditTodoModal} from './TodoModal'


export const TodosTable = () => {

    let todos = useSelector(state => state.todosReducer.todos)
    const dispatch = useDispatch();

    useEffect(() =>{
        GetTodos(dispatch)
    }, [])

    return <table className='table table-dark'>
        <tbody>
            {
                todos.map(n =>
                    <tr key={n.id}>
                        <td style={{width: '3rem'}}>
                            <EditTodoModal todo={n}/>
                        </td>
                        <td style={{width: '3rem'}}>
                            <Button className='btn btn-danger' onClick={() => DeleteTodo(dispatch, n)}>Delete</Button>
                        </td>
                        <td style={{textAlign: 'left'}}>{n.value}</td>
                    </tr>
                    
                    )
            }

        </tbody>

    </table>

}