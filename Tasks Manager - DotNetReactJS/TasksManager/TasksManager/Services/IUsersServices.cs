using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using TasksManager.Model;

namespace TasksManager.Services
{
    public interface IUsersServices
    {
        int Login(string email, string password);
        int Signup(string name, string email, string password);
    }

    
}
