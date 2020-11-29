using System.Collections.Generic;
using System.Linq;
using TodosDb;

namespace TodosCore
{
    public class TodosServices : ITodosServices
    {
        private AppDbContext context;

        public TodosServices(AppDbContext DbContext)
        {
            context = DbContext;
        }
        public Todo GetTodo(int id)
        {
            return context.Todos.First(n => n.Id == id);
        }

        public List<Todo> GetTodos()
        {
            return context.Todos.ToList();
        }
        public Todo CreateTodo(Todo todo)
        {
            context.Add(todo);
            context.SaveChanges();

            return todo;
        }

        public void DeleteTodo(int id)
        {
            var todo = context.Todos.First(n => n.Id == id);
            context.Todos.Remove(todo);
            context.SaveChanges();

        }

        public Todo EditTodo(Todo todo)
        {
            var editedTodo = context.Todos.First(n => n.Id == todo.Id);
            editedTodo.Value = todo.Value;
            context.SaveChanges();
            return editedTodo;
        }

    }
}
