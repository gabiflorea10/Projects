using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace TasksManager.Utils
{
    public class ModifyTodoRequest
    {
        public string UserEmail { get; set; }
        public int TodoId { get; set; }
        public string Note { get; set; }
    }
}
