import * as axios from 'axios';
import React, { Component } from 'react';
import { Button } from 'react-bootstrap';
import { EditTodoModal } from './EditTodoModal';
import { NewTodoModal } from './NewTodoModal';

export class FetchTodos extends Component {
  static displayName = FetchTodos.name;

  constructor(props) {
    super(props);
      this.state = { todos: [], loading: true };
      this.deleteTodo = this.deleteTodo.bind(this);
      this.addTodo = this.addTodo.bind(this);
      this.editTodo = this.editTodo.bind(this);
  }

    async populateTodos() {
        const userEmail = sessionStorage.getItem("currentUser")
        
        const response = await axios.create({ baseURL: 'https://localhost:44352/api/todos' }).post('', { "UserEmail": userEmail })
        
        const data = response.data
        
        this.setState({ todos: data, loading: false });
    }

  componentDidMount() {
    this.populateTodos();
    }

    async deleteTodo(todo) {

        let newTodos = this.state.todos.filter(t => t.todoId !== todo.todoId)
        this.setState({ todos: newTodos });

        const userEmail = sessionStorage.getItem("currentUser")
        const deleteRequestObject = { "UserEmail": userEmail, "TodoId": todo.todoId, "Note": todo.note }
        
        await axios.create({ baseURL: 'https://localhost:44352/api/todos/delete' }).delete('', { data: deleteRequestObject })

 
    }

    async addTodoApi(todoText) {
        const userEmail = sessionStorage.getItem("currentUser")
        const response = await axios.create({ baseURL: 'https://localhost:44352/api/todos/new' }).post('', { "UserEmail": userEmail, "Note": todoText })

        return response
    }

    async addTodo(todoText) {
        // add new todo in table on frontend
        let todo = { "todoId": 0, "note": todoText }
        let newTodos = this.state.todos
        newTodos.push(todo)
        this.setState({ todos: newTodos });

        //call api for update on backend
        
        let response = await this.addTodoApi(todoText);
       

        //update added element in order to be consistent with backend
        let correctId = response.data.todoId;
        let correctTodo = { "todoId": correctId, "note": todoText }
        let correctTodos = this.state.todos
        correctTodos.pop()
        correctTodos.push(correctTodo)

        

        this.setState({ todos: correctTodos });

        
    }


    async editTodo(todo, newTodoText) {

        let newTodos = this.state.todos;

        newTodos = newTodos.map(t => {
            
            if (t.todoId === todo.todoId) {
                t.note = newTodoText;
            }
            return t;
        })

        this.setState({ todos: newTodos });

        const userEmail = sessionStorage.getItem("currentUser")
        await axios.create({ baseURL: 'https://localhost:44352/api/todos/edit' }).put('', { "UserEmail": userEmail, "TodoId": todo.todoId, "Note": newTodoText })

    }

  renderTodosTable(todos) {
      return (
          <div >
      <table className='table table-striped' aria-labelledby="tabelLabel">
        <thead>
          <tr>
            <th>Id</th>
            <th>Task</th>
          </tr>
        </thead>
        <tbody>
          {todos.map((todo, index) =>
              <tr key={todo.todoId}>
                  <td>{index+1}</td>
                  <td>{todo.note}</td>
                  <td style={{ width: '4rem' }}>
                      <EditTodoModal editTodo={this.editTodo} currentTodo={todo}/>
                  </td>
                  <td style={{ width: '4rem' }}>
                      <Button onClick={() => this.deleteTodo(todo)} className='btn btn-danger'>Delete</Button>
                  </td>
            </tr>
          )}
        </tbody>
              </table>
              </div>
    );
  }

  render() {
    let contents = this.state.loading
      ? <p><em>Loading...</em></p>
      : this.renderTodosTable(this.state.todos);

    return (
        <div style={{ marginLeft: '10%', marginRight: '10%' }}>
            <NewTodoModal addTodo={this.addTodo}/>
        <h1 id="tabelLabel" >Tasks</h1>
        {contents}
      </div>
    );
  }


}
