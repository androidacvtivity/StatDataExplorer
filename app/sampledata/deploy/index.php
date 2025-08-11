<?php




 require_once 'connection.php'; 
/**
 * This class will contain methods to receive our http requests, manipulate the
 *  database and send
 * result back to the client
 */
class Scientists{
    /**
     * 1. CONNECT TO DATABASE.
     * 2. RETURN CONNECTION OBJECT
     * 3. IF NO CONNECTION THEN RETURN NULL.
     */
    public function connect()
    {
        $con=new mysqli(Constants::$DB_SERVER,Constants::$USERNAME,Constants::$PASSWORD,
        Constants::$DB_NAME);
        if($con->connect_error) {
            return null;
        }else{
            return $con;
        }
    }

 
    
    public function select()
    {
        $con=$this->connect();
        if($con != null)
        {
            $result=$con->query(Constants::$SQL_SELECT_ALL);
            if($result->num_rows > 0)
            {
                $scientists = array();
                while($row=$result->fetch_array())
                {
                    array_push($scientists, array("id"=>$row['id'],"name"=>$row['name'],
					"description"=>$row['description'],"galaxy"=>$row['galaxy'],"star"=>$row['star']
					,"serviciu"=>$row['serviciu'],"sectia"=>$row['sectia']
					,"depart"=>$row['depart'],"phone"=>$row['phone'],"phoneinternal"=>$row['phoneinternal']
					,"email"=>$row['email'],"personalinfo"=>$row['personalinfo'],"formname"=>$row['formname'],
					"phonemobil"=>$row['phonemobil'],"floor"=>$row['floor'],"office"=>$row['office']));
					
                }
                print(json_encode(array("code" => 1,"message"=>"Success", "result"=>$scientists)));
            }else{
                print(json_encode(array("code" => 0, "message"=>"Data Not Found")));
            }
            $con->close();

        }else{
            print(json_encode(array('code' =>3,
            'message' => 'ERROR: PHP WAS UNABLE TO CONNECT TO MYSQL DUE TO NULL CONNECTION.')));
        }
    }
    
	
	/**
     * This method will:
     * 1. Receive a HTTP POST request from a client.
     * 2. Get the query,limit and start data from that request.
     * 3. Perform a paginated search against our mysql database after connecting.
     * 4. Return a response with either data or error message.
     */
    public function search()
    {
		$query=$_POST['query'];
		$limit=$_POST['limit'];
        $start=$_POST['start'];

		$sql="
	SELECT vb.*
     FROM start3v5 vb
	    WHERE 
			
			 (
			 		 replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(vb.name,'Ţ','T')
,'ţ','t'),'Ş','S'),'ş','s'),'ă','a'),'Î','I'),'Ă','A'),'î','i'),'â','a'),'Â','A')
			 			 LIKE LOWER(TRIM('%$query%')) 
			      or  
             
				 			  replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(vb.phonemobil,'Ţ','T')
,'ţ','t'),'Ş','S'),'ş','s'),'ă','a'),'Î','I'),'Ă','A'),'î','i'),'â','a'),'Â','A')
				 
				 
				 LIKE LOWER(TRIM('%$query%'))
				 
             		 
			 ) 
			 
			
			 AND (statut = 'xxxxx') 
            ORDER BY name
	
		    LIMIT
         $limit OFFSET $start  
		 ";

        $con=$this->connect();
        if($con != null)
        {
            $result=$con->query($sql);
            if($result->num_rows>0)
            {
                $scientists=array();
                while($row=$result->fetch_array())
                {
                    array_push($scientists, array("id"=>$row['id'],"name"=>$row['name'],
                    "description"=>$row['description'],"galaxy"=>$row['galaxy'],"star"=>$row['star'],
					"serviciu"=>$row['serviciu'],"sectia"=>$row['sectia']
					,"depart"=>$row['depart'],"phone"=>$row['phone'],
					"phoneinternal"=>$row['phoneinternal'],"email"=>$row['email'],
					"personalinfo"=>$row['personalinfo']
					,"formname"=>$row['formname'],"phonemobil"=>$row['phonemobil'],"floor"=>$row['floor'],"office"=>$row['office'], "notice"=>$row['notice']  ));
					
					
					
					
					
                }
                print(json_encode(array("code" => 1, "message"=>"Success", "result"=>$scientists)));
            }else{
                print(json_encode(array("code" => 0, "message"=>"Data Not Found")));
            }
            $con->close();

        }else{
            print(json_encode(array('code' =>3,
            'message' => 'ERROR: PHP WAS UNABLE TO CONNECT TO MYSQL DUE TO NULL CONNECTION.')));
        }
    }
	
	
	
	

    
	
    /**
     * This method will handle our HTTP Requests.
     * Basically it checks the request type and then determines the method that needs to be
     * executed. It's kind of a controller.
     */
    public function getStar()
    {
        $sql = "SELECT DISTINCT TRIM(star)  star
FROM start3v5 
WHERE 
star IS NOT NULL 
AND  star != ('empty field') 
AND  star != '' 
GROUP BY 
star
HAVING
star IS NOT NULL 
AND  star != ('empty field') 
AND  star != '' 
ORDER BY star ASC";
        $con = $this->connect();
        if($con != null) {
            $result = $con->query($sql);
            if($result->num_rows > 0) {
                $list = array();
                while($row = $result->fetch_assoc()) {
                    array_push($list, array("star" => $row['star']));
                }
                print(json_encode(array("code" => 1, "message" => "Success", "result" => $list)));
            } else {
                print(json_encode(array("code" => 0, "message" => "No data")));
            }
            $con->close();
        }
    }
    


    public function getEmployeesByStar()
    {
        $star = $_POST['star'];
        $sql = "SELECT * FROM start3v5 WHERE star = ? AND statut = 'xxxxx' ORDER BY name";
        $con = $this->connect();
        if ($con != null) {
            $stmt = $con->prepare($sql);
            $stmt->bind_param('s', $star);
            $stmt->execute();
            $result = $stmt->get_result();
    
            if ($result->num_rows > 0) {
                $employees = array();
                while ($row = $result->fetch_assoc()) {
                    $employees[] = $row;
                }
                print(json_encode(array("code" => 1, "message" => "Success", "result" => $employees)));
            } else {
                print(json_encode(array("code" => 0, "message" => "No employees found for this direction")));
            }
            $con->close();
        } else {
            print(json_encode(array('code' => 3, 'message' => 'ERROR: Cannot connect to database.')));
        }
    }
    
    public function getStructBns($con) {
        if ($con != null) {
            $query = "SELECT id, type, name, name_id FROM struct_bns ORDER BY id ASC";
            $result = $con->query($query);

            $struct = array();
            if ($result && $result->num_rows > 0) {
                while ($row = $result->fetch_assoc()) {
                    $struct[] = array(
                        "id"      => $row['id'],
                        "type"    => $row['type'],
                        "name"    => $row['name'],
                        "name_id" => $row['name_id']
                    );
                }
            }

            echo json_encode($struct, JSON_UNESCAPED_UNICODE);
        } else {
            echo json_encode(array("error" => "Cannot connect to database."));
        }
    }


    public function getEmployeesByStruct($con) {
        $allowedTypes = ['star', 'depart', 'sectia', 'serviciu'];
        $type = isset($_REQUEST['type']) ? $_REQUEST['type'] : null;
        $name = isset($_REQUEST['name']) ? $_REQUEST['name'] : null;

        if (!$type || !$name || !in_array($type, $allowedTypes)) {
            echo json_encode(["error" => "Tip invalid"]);
            return;
        }

        if ($con != null) {
            $stmt = $con->prepare("SELECT * FROM start3v5 WHERE $type = ? AND statut = 'xxxxx' ORDER BY name ASC");
            $stmt->bind_param("s", $name);
            $stmt->execute();
            $result = $stmt->get_result();

            $employees = array();
            while ($row = $result->fetch_assoc()) {
                $employees[] = $row;
            }

            echo json_encode($employees, JSON_UNESCAPED_UNICODE);
        } else {
            echo json_encode(array("error" => "Cannot connect to database."));
        }
    }


    public function handleRequest() {

        if($_SERVER['REQUEST_METHOD'] == 'POST')
        {
            if (isset($_POST['action'])) {

                $action=$_POST['action'];

                if($action == 'INSERT'){
                    $this->insert();
                }else if($action == 'UPDATE'){
                    $this->update();
                }else if($action == 'DELETE'){
                    $this->delete();
                                }

                                else if($action == 'GET_PAGINATED'){
                    $this->search();
                                }else if($action == 'GET_PAGINATED_SEARCH'){
                    $this->search();
                }

                              

                else if($action == 'GET_DISTINCT_STAR'){
                    $this->getStar();
                }


                else if($action == 'GET_EMPLOYEES_BY_STAR'){
                    $this->getEmployeesByStar();
                }

                 // --- NOUL ENDPOINT: struct_bns ---
            else if($action == 'GET_STRUCT_BNS'){
                $con = $this->connect();
                $this->getStructBns($con);
                if($con){ $con->close(); }
            }

            // --- NOUL ENDPOINT: employees_by_struct ---
            else if($action == 'GET_EMPLOYEES_BY_STRUCT'){
                $con = $this->connect();
                $this->getEmployeesByStruct($con);
                if($con){ $con->close(); }
            }

                                else{
                                        print(json_encode(array('code' =>4, 'message' => 'INVALID REQUEST.')));
                                }
            } else{
                                print(json_encode(array('code' =>5, 'message' => 'POST TYPE UNKNOWN.')));
            }

        } else if($_SERVER['REQUEST_METHOD'] == 'GET' && isset($_GET['action'])) {
            $action = $_GET['action'];
            if($action == 'GET_STRUCT_BNS') {
                $con = $this->connect();
                $this->getStructBns($con);
                if($con){ $con->close(); }
            } else if($action == 'GET_EMPLOYEES_BY_STRUCT') {
                $con = $this->connect();
                $this->getEmployeesByStruct($con);
                if($con){ $con->close(); }
            } else {
                $this->select();
            }
        } else {
            $this->select();
        }
    }
}

//Outside the class. Instantiate Scientist then invoke the handleRequest() to listen to our requests.
$s=new Scientists();
$s->handleRequest();

//end




