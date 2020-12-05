using System.Collections.Generic;
using TasksManager.Model;
using TasksManager.Utils;

namespace TasksManager.Services
{
    public interface ITodosServices
    {
        List<Todo> GetTodos();
        List<TodoDTO> GetTodosForUser(string userEmail);
        Todo AddTodoForUser(AddTodoRequest todoRequest);
        Todo EditTodoForUser(ModifyTodoRequest modifyTodo);
        int DeleteTodoForUser(ModifyTodoRequest modifyTodo);
    }
}
