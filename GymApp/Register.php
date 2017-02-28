<?php
	$con = mysqli_connect("localhost","id916098_theteam", "password42","id916098_users");

	$name = $_POST["name"];
	$age = $_POST["age"];
	$username = $_POST["username"];
	$password = $_POST["password"];
	
		

	$statement = mysqli_prepare($con, "INSERT INTO user (name,age,username,password) VALUES (?,?,?,?)");
	mysqli_stmt_bind_param($statement, "siss", $name, $age, $username,$password,weight);
	mysqli_stmt_execute($statement);

	$response = array();
	$response["success"] = true;

	echo json_encode($response);

?>