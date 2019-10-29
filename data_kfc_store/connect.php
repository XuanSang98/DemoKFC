<?php
	$connection = mysqli_connect("localhost","root","","data_store") or die("Error " . mysqli_error($connection));
	mysqli_query($connection,"SET NAMES 'utf8'");
?>