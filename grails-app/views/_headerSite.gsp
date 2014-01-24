<div id='cssmenu'>
<ul>
   <li class='active'><a href='index.html'><span>Home</span></a></li>
   
   <sec:ifLoggedIn>
 	<li><a href='#'><span>See profile</span></a></li>
 	<li><a href='#'><span>Log out</span></a></li>
   </sec:ifLoggedIn>
   
   <sec:ifNotLoggedIn>
   	<li><a href='#'><span>Sign up</span></a></li>
   	<li><a href='#'><span>Log in</span></a></li>
   </sec:ifNotLoggedIn>
   

   <li><a href='#'><span>About</span></a></li>
   <li class='last'><a href='#'><span>Contact</span></a></li>
</ul>
</div>