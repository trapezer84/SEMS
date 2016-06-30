<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--<c:set var="root" value="${pageContext.request.contextPath}" /> --%>
<!DOCTYPE html>
<!--[if IE 9 ]><html class="ie9"><![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Education Calendar</title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/vendors/auto-size/jquery.autosize.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/vendors/fileinput/fileinput.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/vendors/input-mask/input-mask.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/vendors/farbtastic/farbtastic.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/vendors/summernote/summernote.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/vendors/noUiSlider/jquery.nouislider.all.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/vendors/chosen/chosen.jquery.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/vendors/bootstrap-select/bootstrap-select.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/vendors/moment/moment.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/vendors/nicescroll/jquery.nicescroll.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/vendors/fullcalendar/lib/moment.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/vendors/fullcalendar/fullcalendar.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/vendors/waves/waves.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/vendors/bootstrap-growl/bootstrap-growl.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/vendors/sweet-alert/sweet-alert.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js' />"></script>
<script type="text/javascript">
   $(document).ready(
               function() {
                  var today = new Date();
                  var date = new Date();
                  var start = new Date();
                  var end = new Date();
                  var d = date.getDate();
                  var m = date.getMonth();
                  var y = date.getFullYear();

                  var cId = $('#calendar'); //Change the name if you want. I'm also using thsi add button for more actions
                  
                  var calNotices = new Array();
                  
                  var calNotice = {};
                  calNotice.title = "${education.educationTitle}";
                  calNotice.start = "${education.startDate}";
                  calNotice.end = "${education.endDate}";
                  calNotice.className = "${bgm-red}";
                  calNotices.push(calNotice);

                  // 내가 현재 수강하고 있는 과목에 대한 정보
                  <c:forEach items="${myEducations}" var="myEducation">
                  		alert("${myEducation.educationId}");
                  			var myEducationInfo = {};
                  			var myEdu = '*현재 수강 강의';
                    	 myEducationInfo.title = myEdu+"${myEducation.educationTitle}";
                    	 myEducationInfo.start = "${myEducation.startDate}";
                    	 myEducationInfo.end = "${myEducation.endDate}";
                    	 myEducationInfo.className = "${bgm-red}";
                    	 calNotices.push(myEducationInfo);
					</c:forEach>
                   
                  //Generate the Calendar
                  cId.fullCalendar({
                           header : {
                              right : '',
                              center : 'prev, title, next',
                              left : ''
                           },
                           defaultView : 'month',
                           dayRender : function(date, cell) {
                              
                              end.setDate(today.getDate() + 7);
                              var cellDate = $(cell).data('date');
                              var todayDate;

                              if (m + 1 < 10) {
                                 if (d < 10) {
                                    todayDate = y + '-' + '0' + (m + 1) + '-' + '0' + d;
                                 } 
                                 else {
                                    todayDate = y + '-' + '0' + (m + 1) + '-' + d;
                                 }
                              } 
                              else {
                                 if (d < 10) {
                                    todayDate = y + '-' + (m + 1) + '-' + '0' + d;
                                 } 
                                 else {
                                    todayDate = y + '-' + (m + 1) + '-' + d;
                                 }
                              }
                              //console.log("cellDate" + cellDate);
                              //console.log("todayDate" + todayDate);

                              if (cellDate == todayDate) {
                                 
                                 cell.css("background-color", "#a5cefc");
                              }
                           },

                           fixedWeekCount : false,
                           contentHeight : 370,
                           eventLimit : true,
                           theme : true, //Do not remove this as it ruin the design
                           selectable : true,
                           selectHelper : true,
                           select : true,
                           editable : true,

                           //Add Events
                           events :
										calNotices,
										
                           //On Day Select
                               select: function(start, end, allDay) {
                                   $('#addNew-event').modal('show');   
                                   $('#addNew-event input:text').val('');
                                   $('#getStart').val(start);
                                   $('#getEnd').val(end);
                               }
                           });
                                    
                                     //Event Tag Selector
                                     (function(){
                                         $('body').on('click', '.event-tag > span', function(){
                                             $('.event-tag > span').removeClass('selected');
                                             $(this).addClass('selected');
                                         });
                                     })();
                                 
                                     //Add new Event
                                     $('body').on('click', '#addEvent', function(){
                                         var eventName = $('#eventName').val();
                                         var tagColor = $('.event-tag > span.selected').attr('data-tag');
                                             
                                         if (eventName != '') {
                                             //Render Event
                                             $('#calendar').fullCalendar('renderEvent',{
                                                 title: eventName,
                                                 start: $('#getStart').val(),
                                                 end:  $('#getEnd').val(),
                                                 allDay: true,
                                                 className: tagColor
                                                 
                                             },true ); //Stick the event
                                             
                                             $('#addNew-event form')[0].reset()
                                             $('#addNew-event').modal('hide');
                                         }
                                         
                                         else {
                                             $('#eventName').closest('.form-group').addClass('has-error');
                                         }
                                     }); 
                                     
                                   //today click
                                     $('#my-today-button').click(function() {
                                     $('#calendar').fullCalendar('today');
                                 });

                                     //Calendar views
                                     $('body').on('click', '#fc-actions [data-view]', function(e){
                                         e.preventDefault();
                                         var dataView = $(this).attr('data-view');
                                         
                                         $('#fc-actions li').removeClass('active');
                                         $(this).parent().addClass('active');
                                         cId.fullCalendar('changeView', dataView);  
                                     });
                                 });                        
                             </script>
                       

<!-- Vendor CSS -->
<link href="<c:url value='/resources/vendors/animate-css/animate.min.css'/>" rel="stylesheet">
<link href="<c:url value='/resources/vendors/fullcalendar/fullcalendar.css' />" rel="stylesheet">
<link href="<c:url value='/resources/vendors/sweet-alert/sweet-alert.min.css' />" rel="stylesheet">
<link href="<c:url value='/resources/vendors/material-icons/material-design-iconic-font.min.css'/>" rel="stylesheet">
<link href="<c:url value='/resources/vendors/socicon/socicon.min.css'/>" rel="stylesheet">
<link href="<c:url value='/resources/vendors/noUiSlider/jquery.nouislider.min.css'/>" rel="stylesheet">
<link href="<c:url value='/resources/vendors/farbtastic/farbtastic.css'/>" rel="stylesheet">
<link href="<c:url value='/resources/vendors/summernote/summernote.css'/>" rel="stylesheet">
<!-- CSS -->
<link href="<c:url value='/resources/css/app.min.2.css'/>" rel="stylesheet">
<link href="<c:url value='/resources/css/bottom.css'/>" rel="stylesheet">
<link href="<c:url value='/resources/css/eduDetail.css'/>" rel="stylesheet">

</head>
</html>