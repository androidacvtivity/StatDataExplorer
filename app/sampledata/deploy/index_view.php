<?php

require_once 'connection.php'; 
/**
 * This class will contain methods to receive our http requests, manipulate the
 *  database and send
 * resultvw back to the client
 */
class Scientistsvw{
    /**
     * 1. CONNECT TO DATABASE.
     * 2. RETURN CONNECTION OBJECT
     * 3. IF NO CONNECTION THEN RETURN NULL.
     */
    public function connectvw()
    {
        $con=new mysqli(Constants::$DB_SERVER,Constants::$USERNAME,Constants::$PASSWORD,
        Constants::$DB_NAME);
        if($con->connect_error) {
            return null;
        }else{
            return $con;
        }
    }

    
    
   
    /**
     * This method will:
     * 1. Connect to MySQL.
     * 2. Select all data from database.
     * 3. Return those data as a response.
     */
    public function selectvw()
    {
        $con=$this->connectvw();
        if($con != null)
        {
            $resultvw=$con->query(Constants::$SQL_SELECT_ALL_VIEW);
            if($resultvw->num_rows > 0)
            {
                $scientistsvw = array();
                while($row=$resultvw->fetch_array())
                {
                    array_push($scientistsvw, array("id_vw"=>$row['id_vw'],"DEN_COM_VW"=>$row['DEN_COM_VW'],
					"IDNO_VW"=>$row['IDNO_VW'],"ADRESA_VW"=>$row['ADRESA_VW'],"FORMA_ORG_VW"=>$row['FORMA_ORG_VW']
					,"LIST_COND_VW"=>$row['LIST_COND_VW'],"LISTA_FOND_VW"=>$row['LISTA_FOND_VW']
					,"GEN_ACT_NE_LIC_VW"=>$row['GEN_ACT_NE_LIC_VW'],"GEN_ACT_LIC_VW"=>$row['GEN_ACT_LIC_VW'],"STATUTUL_VW"=>$row['STATUTUL_VW']
					,"DATA_REG_VW"=>$row['DATA_REG_VW'],"personalinfo"=>$row['personalinfo'],"act"=>$row['act']));
					
                }
                print(json_encode(array("codevw" => 1,"messagevw"=>"Success", "resultvw"=>$scientistsvw)));
            }else{
                print(json_encode(array("codevw" => 0, "messagevw"=>"Data Not Found")));
            }
            $con->close();

        }else{
            print(json_encode(array('codevw' =>3,
            'messagevw' => 'ERROR: PHP WAS UNABLE TO CONNECT TO MYSQL DUE TO NULL CONNECTION.')));
        }
    }
    
	
	//http://172.20.1.154/
	
	
	public function searchvw()
    {
		$queryvw=$_POST['queryvw'];
		$limitvw=$_POST['limitvw'];
        $startvw=$_POST['startvw'];



		$sql="SELECT * FROM reg_05_12_25_vw	


		
		
		WHERE 
		 
		 DEN_COM_VW LIKE '%$queryvw%' 
		 or LISTA_FOND_VW LIKE '%$queryvw%'
		 or IDNO_VW LIKE '%$queryvw%'
		 or GEN_ACT_NE_LIC_VW LIKE '%$queryvw%'
		 or ADRESA_VW LIKE '%$queryvw%'
		 or GEN_ACT_LIC_VW LIKE '%$queryvw%'
		 or STATUTUL_VW LIKE '%$queryvw%'
		 or DATA_REG_VW LIKE '%$queryvw%'

         ORDER BY ID_VW
	     LIMIT
         $limitvw OFFSET $startvw";

        $con=$this->connectvw();
        if($con != null)
        {
            $resultvw=$con->query($sql);
            if($resultvw->num_rows>0)
            {
                $scientistsvw=array();
                while($row=$resultvw->fetch_array())
                {
                   
					
					
					 array_push($scientistsvw, array("id_vw"=>$row['id_vw'],"DEN_COM_VW"=>$row['DEN_COM_VW'],
					"IDNO_VW"=>$row['IDNO_VW'],"ADRESA_VW"=>$row['ADRESA_VW'],"FORMA_ORG_VW"=>$row['FORMA_ORG_VW']
					,"LIST_COND_VW"=>$row['LIST_COND_VW'],"LISTA_FOND_VW"=>$row['LISTA_FOND_VW']
					,"GEN_ACT_NE_LIC_VW"=>$row['GEN_ACT_NE_LIC_VW'],"GEN_ACT_LIC_VW"=>$row['GEN_ACT_LIC_VW'],"STATUTUL_VW"=>$row['STATUTUL_VW']
					,"DATA_REG_VW"=>$row['DATA_REG_VW'],"personalinfo"=>$row['personalinfo'],"act"=>$row['act']));
					
					
					
					
                }
                print(json_encode(array("codevw" => 1, "messagevw"=>"Success", "resultvw"=>$scientistsvw)));
            }else{
                print(json_encode(array("codevw" => 0, "messagevw"=>"Data Not Found")));
            }
            $con->close();

        }else{
            print(json_encode(array('codevw' =>3,
            'messagevw' => 'ERROR: PHP WAS UNABLE TO CONNECT TO MYSQL DUE TO NULL CONNECTION.')));
        }
    }
	
	
	
    /**
     * This method will handle our HTTP Requests.
     * Basically it checks the request type and then determines the method that needs to be
     * executed. It's kind of a controller.
     */
    public function handleRequest() {

        if($_SERVER['REQUEST_METHOD'] == 'POST')
        {
            if (isset($_POST['actionvw'])) {

                $actionvw=$_POST['actionvw'];

                if($actionvw == 'INSERT'){
                    $this->insert();
                }else if($actionvw == 'UPDATE'){
                    $this->update();
                }else if($actionvw == 'DELETE'){
                    $this->delete();
				}else if($actionvw == 'GET_PAGINATEDVW'){
                    $this->searchvw();
				}else if($actionvw == 'GET_PAGINATED_SEARCHVW'){
                    $this->searchvw();
                }else{
					print(json_encode(array('codevw' =>4, 'messagevw' => 'INVALID REQUEST.')));
				}
            } else{
				print(json_encode(array('codevw' =>5, 'messagevw' => 'POST TYPE UNKNOWN.')));
            }

        }else{
            $this->selectvw();
        }
    }
}

//Outside the class. Instantiate Scientist then invoke the handleRequest() to listen to our requests.
$s=new Scientistsvw();
$s->handleRequest();

//end




