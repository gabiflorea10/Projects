using System;
using System.Collections.Generic;
using System.Text;
using TodosDb;

namespace TodosCore
{
    public interface ITodosServices
    {
        Todo CreateTodo(Todo todo);
        Todo GetTodo(int id);

        List<Todo> GetTodos();

        void DeleteTodo(int id);

        Todo EditTodo(Todo todo);
       
    }
}
