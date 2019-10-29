<?php
	include "connect.php";
	$query = "SELECT * FROM danhsachthucdon";
	$mangloaithucdon = array();
	$data = mysqli_query($connection,$query);
	while($row = mysqli_fetch_assoc($data)){
		array_push($mangloaithucdon,new Loaithucdon(
			$row['ID_ThucDon'],
			$row['Ten_Thuc_Don'],
			$row['HinhAnh_ThucDon'],
			$row['Trang_Thai']
		));
	}
	echo json_encode($mangloaithucdon);
	class Loaithucdon{
		function Loaithucdon($ID_ThucDon,$Ten_Thuc_Don,$HinhAnh_ThucDon,$Trang_Thai){
			$this->ID_ThucDon =$ID_ThucDon;
			$this->Ten_Thuc_Don =$Ten_Thuc_Don;
			$this->HinhAnh_ThucDon =$HinhAnh_ThucDon;
			$this->Trang_Thai =$Trang_Thai;
		}
	}
?>