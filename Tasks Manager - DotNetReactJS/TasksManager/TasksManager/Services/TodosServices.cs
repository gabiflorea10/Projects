using System;
using System.Collections.Generic;
using System.Linq;
using TasksManager.DbAccess;
using TasksManager.Model;
using TasksManager.Utils;

namespace TasksManager.Services
{
    public class TodosServices : ITodosServices
    {
        private TodosDbContext context;

        public TodosServices(TodosDbContext dbContext)
        {
            this.context = dbContext;
        }

        public List<Todo> GetTodos()
        {
            return context.Todos.ToList();
        }

        public TodoDTO todoToDto(Todo todo)
        {
            TodoDTO todoDTO = new TodoDTO();

            todoDTO.TodoId = todo.TodoId;
            todoDTO.Note = todo.Note;

            return todoDTO;
        }

        public List<TodoDTO> GetTodosForUser(string userEmail)
        {

            User user = context.Users.FirstOrDefault(n => String.Equals(n.Email, userEmail));


            List<UserTodo> utList = context.UserTodos.Where(ut => ut.UserId == user.UserId).ToList();

            List<TodoDTO> todoList = new List<TodoDTO>();

            foreach (UserTodo ut in utList)
            {
                Todo currentTodo = context.Todos.FirstOrDefault(t => t.TodoId == ut.TodoId);
                todoList.Add(todoToDto(currentTodo));
                
            }

            return todoList;
        }

        public Todo AddTodoForUser(AddTodoRequest todoRequest)
        {
            string userEmail = todoRequest.UserEmail;
            User user = context.Users.FirstOrDefault(n => String.Equals(n.Email, userEmail));
            Todo todo = new Todo();
            todo.Note = todoRequest.Note;
            context.Todos.Add(todo);
            context.SaveChanges();

            int todoId = todo.TodoId;

            UserTodo userTodo = new UserTodo();
            userTodo.UserId = user.UserId;
            userTodo.TodoId = todoId;

            context.UserTodos.Add(userTodo);
            context.SaveChanges();

            return todo;


        }

        public Todo EditTodoForUser(ModifyTodoRequest modifyTodo)
        {
            Todo todo = context.Todos.FirstOrDefault(t => t.TodoId == modifyTodo.TodoId);
            todo.Note = modifyTodo.Note;
            context.SaveChanges();
            return todo;

        }

        public int DeleteTodoForUser(ModifyTodoRequest modifyTodo)
        {
            User user = context.Users.FirstOrDefault(n => String.Equals(n.Email, modifyTodo.UserEmail));
            Todo todo = context.Todos.FirstOrDefault(t => t.TodoId == modifyTodo.TodoId);
            int userId = user.UserId;
            int todoId = todo.TodoId;
            UserTodo userTodo = context.UserTodos.FirstOrDefault(ut => ut.UserId == userId && ut.TodoId == todoId);

            context.UserTodos.Remove(userTodo);
            context.Todos.Remove(todo);
            context.SaveChanges();
            return 1;

        }


    }
}
