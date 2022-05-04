import Carousel from 'react-multi-carousel';
//import { Image } from "semantic-ui-react";
import { Image, ListGroup, ListGroupItem } from 'react-bootstrap';
import JsonDataDisplay from './ShowEvents';
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Card from "react-bootstrap/Card";
import Button from "react-bootstrap/Button";
import '../styles/showEvents.css';
import '../styles/showEventCat.css';


const responsive = {
  desktop: {
    breakpoint: { max: 3000, min: 1024 },
    items: 3,
    paritialVisibilityGutter: 60
  },
  tablet: {
    breakpoint: { max: 1024, min: 464 },
    items: 2,
    paritialVisibilityGutter: 50
  },
  mobile: {
    breakpoint: { max: 464, min: 0 },
    items: 1,
    paritialVisibilityGutter: 30
  }
};
const images = [
  "https://images.unsplash.com/photo-1549989476-69a92fa57c36?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60",
  "https://images.unsplash.com/photo-1549396535-c11d5c55b9df?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=60",
  "https://images.unsplash.com/photo-1550133730-695473e544be?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60",
  "https://images.unsplash.com/photo-1550167164-1b67c2be3973?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60",
  "https://images.unsplash.com/photo-1550338861-b7cfeaf8ffd8?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60",
  "https://images.unsplash.com/photo-1550223640-23097fc71cb2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60",
  "https://images.unsplash.com/photo-1550353175-a3611868086b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60",
  "https://images.unsplash.com/photo-1550330039-a54e15ed9d33?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60",
  "https://images.unsplash.com/photo-1549737328-8b9f3252b927?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60",
  "https://images.unsplash.com/photo-1549833284-6a7df91c1f65?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60",
  "https://images.unsplash.com/photo-1549985908-597a09ef0a7c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60",
  "https://images.unsplash.com/photo-1550064824-8f993041ffd3?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
];

//const cat = [];

export default function DataCat() {
  const [appState, setAppState] = useState({
    loading: false,
    cat: [] as any[]
  });

  useEffect(() => {
      setAppState({loading: true, cat: []});
      const apiUrl = 'http://127.0.0.1:8080/vowel_events';
      axios.get(apiUrl).then(res => {
          const allCategories = res.data
          setAppState({ loading: false, cat: allCategories });  
      })
  }, [setAppState])

  return (
    <ListGroup>
      {appState.cat.map(c => (
        <ListGroupItem><h1>{c.category}</h1>
          <Carousel
            //ssr
            partialVisbile
            /*deviceType={deviceType}*/
            //itemClass="image-item"
            responsive={responsive} 
            className="carousel"
          >
          {c.events.map((e: { imageUrl: string; name: string; date: string; city: string; }) => {
              return (
                <Card>
                    <Card.Body className='c-body'>
                        <Card.Img variant="top" src={e.imageUrl} />
                        <Card.Title className='c-title'><h1>{e.name}</h1> </Card.Title>
                    </Card.Body>
                    
                    <Card.Footer className='c-footer'>
                        <Button>Köp</Button>
                        <Card.Text><p>{e.date}, {e.city}</p></Card.Text>
                    </Card.Footer>
                </Card>
              );
            })}
          </Carousel>
        </ListGroupItem>
        ))}
    </ListGroup>
  );

}


// Because this is an inframe, so the SSR mode doesn't not do well here.
// It will work on real devices.
/*
const Simple = ({  }) => {
  return (
    <Carousel
      ssr
      partialVisbile
      deviceType={deviceType}
      itemClass="image-item"
      responsive={responsive}
    >
      {images.slice(0, 5).map(image => {
        return (
          <Image
            draggable={false}
            style={{ width: "100%", height: "100%" }}
            src={image}
          />
        );
      })}
    </Carousel>
  );
};

export default Simple;
*/

/*
          {c.event.map((e: { imageUrl: string | undefined; }) => {
            return (
              <Card>
                  <Card.Body className='c-body'>
                      <Card.Img variant="top" src={e.imageUrl} />
                      <Card.Title className='c-title'><h1>{"hello"}</h1> </Card.Title>
                  </Card.Body>
                  
                  <Card.Footer className='c-footer'>
                      <Button>Köp</Button>
                      <Card.Text><p>{"hej"}, {"hola"}</p></Card.Text>
                  </Card.Footer>
              </Card>
            );
          })}
          */
