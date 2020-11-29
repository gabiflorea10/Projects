using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using TodosCore;
using TodosDb;

namespace todoapi.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class TodosController : ControllerBase
    {

        private readonly ILogger<TodosController> _logger;
        private ITodosServices todosServices;
        public TodosController(ILogger<TodosController> logger, ITodosServices iTodosServices)
        {
            _logger = logger;
            todosServices = iTodosServices;
        }


        [HttpGet]
        public IActionResult GetTodos()
        {
            return Ok(todosServices.GetTodos());
        }


        [HttpGet("{id}", Name = "GetTodo")]
        public IActionResult GetTodo(int id)
        {
            return Ok(todosServices.GetTodo(id));
        }

        [HttpPost]
        public IActionResult CreateTodo(Todo todo)
        {
            var newTodo = todosServices.CreateTodo(todo);
            return CreatedAtRoute("GetTodo", new { newTodo.Id }, newTodo);
        }

        [HttpDelete("{id}")]
        public IActionResult DeleteTodo(int id)
        {
            todosServices.DeleteTodo(id);
            return Ok();
        }


        [HttpPut]
        public IActionResult EditTodo([FromBody] Todo todo)
        {
            todosServices.EditTodo(todo);
            return Ok();
        }



    }
}
