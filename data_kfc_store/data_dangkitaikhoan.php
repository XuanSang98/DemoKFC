<?php
    //open connection to mysql db
    $connection = mysqli_connect("localhost","root","","data_kfc_store") or die("Error " . mysqli_error($connection));
	mysqli_set_charset($connection,"utf8");
	header('Content-Type: application/json; charset=utf-8');
    //fetch table rows from mysql db
    $sql = "SELECT Email,Mat_Khau FROM `dangki_taikhoan`";
    $result = mysqli_query($connection, $sql) or die("Error in Selecting " . mysqli_error($connection));
 
    //create an array
    $emptyarray[] = array();
    while($row =mysqli_fetch_assoc($result))
    {
        $emptyarray[] = $row;
    }
    echo json_encode($emptyarray);
 
    //close the db connection
    mysqli_close($connection);
?>