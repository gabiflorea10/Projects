using Assignment4DotNet.DataLayer;
using Assignment4DotNet.Model;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace Assignment4DotNet
{
    /// <summary>
    /// Summary description for CaregiverService
    /// </summary>
    [WebService(Namespace = "http://ds.Assignment4/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]


    public class CaregiverService : System.Web.Services.WebService
    {

        [WebMethod]
        public List<RecommendationDTO> viewRecommendations(String caregiver)
        {
            DataAccess dataAccess = new DataAccess();

            return dataAccess.getRecommendationData(caregiver);
        }
    }
}
