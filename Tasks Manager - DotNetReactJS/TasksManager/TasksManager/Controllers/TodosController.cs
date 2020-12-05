using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using TasksManager.Model;
using TasksManager.Services;
using TasksManager.Utils;

namespace TasksManager.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class TodosController : ControllerBase
    {
        private ITodosServices services;

        public TodosController(ITodosServices todosServices)
        {
            this.services = todosServices;
        }


        [HttpPost]
        public IActionResult GetTodosForUser([FromBody] TodoRequest todoRequest)
        {
            return Ok(services.GetTodosForUser(todoRequest.UserEmail));
        }

        [Route("new")]
        [HttpPost]
        public IActionResult AddTodoForUser([FromBody] AddTodoRequest newTodo)
        {
            return Ok(services.AddTodoForUser(newTodo));
        }

        [Route("edit")]
        [HttpPut]
        public IActionResult EditTodoForUser([FromBody] ModifyTodoRequest todo)
        {
            return Ok(services.EditTodoForUser(todo));
        }

        [Route("delete")]
        [HttpDelete]
        public IActionResult DeleteTodoForUser([FromBody] ModifyTodoRequest todo)
        {
            return Ok(services.DeleteTodoForUser(todo));
        }



    }
}
