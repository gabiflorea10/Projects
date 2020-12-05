using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace TasksManager.Utils
{
    public class AddTodoRequest
    {
        public string UserEmail { get; set; }
        public string Note { get; set; }
    }
}
