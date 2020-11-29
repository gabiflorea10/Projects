import React from 'react';
import './App.css';
import { TodosTable } from './components/TodosTable';
import {NewTodoModal} from './components/TodoModal'

const App = () => {
  return (
    <div className="App">
      <h2> To Do</h2>
      <div style={{ maxWidth: '70%', margin: 'auto' }}>
        <div style={{textAlign: 'right'}}>
          <NewTodoModal/>
        </div>
        <TodosTable />
      </div>


    </div>
  );
}

export default App;
