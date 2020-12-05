using System.ComponentModel.DataAnnotations;

namespace TasksManager.Model
{
    public class UserTodo
    {
        [Key]
        public int UserTodoId { get; set; }
        public int TodoId { get; set; }
        public Todo Todo { get; set; }
        public int UserId { get; set; }
        public User User { get; set; }
    }
}
