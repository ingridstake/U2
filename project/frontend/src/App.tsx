import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
const options = {
  //look att http methods if u dont understand this
  method: "GET",
  //look up cors
  mode: "cors" as RequestMode,
};
function App() {
  const [greeting, setGreeting] = useState("Loading")
  useEffect(() => {
    //use something like axios instead
    fetch('http://127.0.0.1:8080/hello?myName=U2', options).then(async res => await res.text()).then(text => setGreeting(text))

  },[])
  return (
    <>
    <p>{greeting}</p>
    </>
  );
}

export default App;
