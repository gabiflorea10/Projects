using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace TasksManager.Model
{
    public class User
    {
        [Key]
        public int UserId { get; set; }
        public string Name { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }
        public ICollection<UserTodo> UserTodos { get; set; }
    }
}
