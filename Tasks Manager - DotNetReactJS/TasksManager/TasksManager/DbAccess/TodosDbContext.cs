using Microsoft.EntityFrameworkCore;
using TasksManager.Model;

namespace TasksManager.DbAccess
{
    public class TodosDbContext: DbContext
    {

        public DbSet<Todo> Todos { get; set; }
        public DbSet<User> Users { get; set; }
        public DbSet<UserTodo> UserTodos { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer(
                "Server=(localdb)\\mssqllocaldb;Database=Todos;Integrated Security=True");
        }


    }
}
