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
    public class UsersController : ControllerBase
    {
        private IUsersServices usersServices;

        public UsersController(IUsersServices iUserServices)
        {
            this.usersServices = iUserServices;
        }

        [HttpPost]
        public IActionResult LoginUser([FromBody] LoginRequest user)
        {
            int loginStatus = usersServices.Login(user.Email, user.Password);
            return Ok(loginStatus);
        }

        [Route("signup")]
        [HttpPost]
        public IActionResult SignUpUser([FromBody] SignupRequest user)
        {
            int signupStatus = usersServices.Signup(user.Name, user.Email, user.Password);
            return Ok(signupStatus);
        }


    }
}
