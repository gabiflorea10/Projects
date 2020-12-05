using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace TasksManager.Model
{
    public class Todo
    {   
        [Key]
        public int TodoId { get; set; }
        public string Note { get; set; }
        public ICollection<UserTodo> UserTodos { get; set; }
    }
}
