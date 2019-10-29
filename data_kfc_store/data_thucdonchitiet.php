<?php
	include "connect.php";
	$page =$_GET['page'];
	$idsp = $_POST['ID_ThucDon'];
	$space = 6;
	$limit = ($page - 1)*$space;
	$mangsanpham = array();
	$query ="SELECT * FROM thucdonchitiet WHERE ID_ThucDon =$idsp LIMIT $limit,$space";
	$data = mysqli_query($connection,$query);
	while($row = mysqli_fetch_assoc($data)){
		array_push($mangsanpham,new Sanpham(
			$row['ID_ThucDonChiTiet'],
			$row['ID_ThucDon'],
			$row['Ten_ChiTiet'],
			$row['Gia_Tien'],
			$row['Hinh_Anh']
		));
	}
	echo json_encode($mangsanpham);
	class Sanpham{
		function Sanpham($ID_ThucDonChiTiet,$ID_ThucDon,$Ten_ChiTiet,$Gia_Tien,$Hinh_Anh){
			$this->ID_ThucDonChiTiet = $ID_ThucDonChiTiet;
			$this->ID_ThucDon = $ID_ThucDon;
			$this->Ten_ChiTiet = $Ten_ChiTiet;
			$this->Gia_Tien = $Gia_Tien;
			$this->Hinh_Anh = $Hinh_Anh;
		}
	}
?>