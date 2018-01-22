var app=angular.module("MyApp",['ui.router']);

app.config(function($stateProvider,$urlRouterProvider,$locationProvider){
	$stateProvider.state('home',{
		url:'/home',
		templateUrl:'views/home.html',
		controller:'HomeController'
	});
	
	//$locationProvider.html5Mode(true);

	
	$stateProvider.state('chercher',{
		url:'/chercher',
		templateUrl:'views/chercher.html',
		controller:'MyController'
	});
	
	
	
	$stateProvider.state('GestionDemande',{
		url:'/GestionDemande',
		templateUrl:'views/GestionDemande.html',
		controller:'DemandeController'
	});
	
	
});


app.controller("DemandeController",function(){
	$scope.siz=1;
	$scope.pageCourant=0;
	$scope.pags=[];
	$scope.ListAssociationsAttente=null;
	$scope.chercherAssociationAttente=function(){
		$http({
		      method: 'GET',
		      url: 'http://localhost:8080/AssociationEtatAttente?et='+1+'&page='+$scope.pageCourant+'&size='+$scope.siz
		   }).then(function (response){
			   $scope.ListAssociationsAttente=response.data.content;
			   $scope.pags=new Array(response.data.totalPages);
		   },function (error){
			   console.log(error);
		   });
	}
});

app.controller("HomeController",function(){
});





app.controller("MyController",function($scope,$http,$window,$location)
{
	$scope.pageAssociations=null;
	$scope.motCle="";
	$scope.pageCourante=0;
	$scope.size=1;
	$scope.pages=[];
	
	 $scope.chercherAssociation=function(){
		$http({
		      method: 'GET',
		      url: 'http://localhost:8080/chercherAssociations?mc='+$scope.motCle+'&page='+$scope.pageCourante+'&size='+$scope.size+''
		   }).then(function (response){
			   $scope.pageAssociations=response.data.content;
			   $scope.pages=new Array(response.data.totalPages);
		   },function (error){
			   console.log(error);
		   });
	}
	 
	 
	 
	 
	 $scope.ListAll=function(){
			$http({
			      method: 'GET',
			      url: 'http://localhost:8080/chercherAssociations?mc= '
			   }).then(function (response){	   
				   $scope.pageAssociations=response.data.content;
				   $scope.pages=new Array(response.data.totalPages);
			   },function (error){
				   console.log(error);
			   });
		}
	 
	 
	$scope.gotoPage=function(p)
	{
		$scope.pageCourante=p;
		$scope.chercherAssociation();
	}
	
	$scope.SupprimerAssociation=function(id)
	{
		$http({
		      method: 'DELETE',
		      url: 'http://localhost:8080/associations/'+id
		   }).then(function (response){
			   alert('association deleted');			  
		   },function (error){
			   console.log(error);
		   });
	}
	
	$scope.associat={};
	$scope.etat="";
	$scope.associationAmodifier=null;
	$scope.saveAssociation=function()
	{
		$scope.etat=document.getElementById("btnAjout").getAttribute("value");
		//alert('etat ====== '+$scope.etat);
		if($scope.etat=="ajouter")
		{
		$http({
		      method: 'POST',
		      url: 'http://localhost:8080/associationSave',
		      data: $scope.associat,
		      dataType: 'json',
              headers: { "Content-Type": "application/json" }
		   })
		.then(function (response){
			   alert('association : '+response.data.nom+'est ajoutée');
		   },function (error){
			   console.log(error);
		   });
		}
		else
			{
			//alert('id ==== '+$scope.associat.id);
			$http({
			      method: 'PUT',
			      url: 'http://localhost:8080/associationsModify/'+$scope.associat.id,
			      data: $scope.associat,
			      dataType: 'json',
	              headers: { "Content-Type": "application/json" }
			   })
				.then(function (response){
					    alert(response.data.nom);
						document.getElementById("btnAjout").setAttribute("value","ajouter");
				   },function (error){
					   console.log(error);
				   });
			}
	}
	
	
	
	$scope.RecupererAssociation=function(id)
	{
		
		document.getElementById("btnAjout").setAttribute("value","modifier");
		$http({
		      method: 'GET',
		      url: 'http://localhost:8080/associations/'+id
		   }).then(function (response){
			   $scope.associat=response.data;
		   },function (error){
			   console.log(error);
		   });
	}
	
	/*$scope.RecupererAssociation=function(id)
	{
		$scope.associationAmodifier=null;
		$http({
		      method: 'GET',
		      url: 'http://localhost:8080/associations/'+id+''
		   }).then(function (response){
			   $scope.associationAmodifier=response.data;
			   alert($scope.associationAmodifier.nom);
			   $window.location.href = '../EditAssociation.html?val='+id;
		   },function (error){
			   console.log(error);
		   });
	}*/
	
	/*$scope.goToPage=function(id)
	{
		   $window.location.href = '../EditAssociation.html';
		   $scope.RecupererAssociation(id);
	}*/
	
});