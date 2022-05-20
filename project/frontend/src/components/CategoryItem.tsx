import Carousel from 'react-multi-carousel';
import { ListGroupItem } from 'react-bootstrap';
import '../styles/showEventCat.css';
import { EventCards } from './EventCards';
import { CategoryTagButtons } from './CategoryTagButtons';
import { category, event } from './Models'

/**
 * A Category represented as a ListGroupItem.
 * Contains a category title, tags as CategoryTagButtons and events in a carousel as EvenCards.
 * @param c 
 * @param onClick is the filtering function
 * @returns a ListGroupItem containing a category and its components; title, tags and events
 */
export const CategoryItem = (c: category, onClick: (activeCategory: category, tagName: string) => void) => {
    const onTagClick = (tagName: string) => {
        onClick(c, tagName)
    }

    const filteredData = (): event[] => {
        const selected = c.selectedTags
        if (!selected || selected.length === 0) return c.events      // if the selectedTags is undefined or empty; return all events
        return c.events.filter(e => {                               // otherwise; only return events with selected tags (inclusive)    
            return selected.some(tag => e.e_tags.includes(tag))
        }
        /*
        return c.events.filter(e => {                               // otherwise; only return events with selected tags (exclusive)           
            return selected.every(tag => e.e_tags.includes(tag))
        }
        */
        )
    }
    
    return (
        <ListGroupItem>
            <div className="category-title">
                <h1 className="cat-title">{c.category}</h1>
                <div className='cat-tags'>{CategoryTagButtons(c.tags, c.selectedTags, onTagClick)}</div>
            </div>

            <Carousel
                responsive={responsive}
                showDots={true}
                /*infinite={true}*/
                shouldResetAutoplay={false}
                customTransition="1000ms cubic-bezier(0.645, 0.045, 0.355, 1) 0s"
            >
                {EventCards(filteredData())}
            </Carousel>
        </ListGroupItem>
    );
}

/**
 * Responsive item settings for different platform sizes.
 * Used in Carousel.
 */
const responsive = {
    desktopBig: {
        breakpoint: { max: 3000, min: 1550 },
        items: 4,
        slidesToSlide: 4,
    },
    desktopSmall: {
        breakpoint: { max: 1550, min: 1048 },
        items: 3,
        slidesToSlide: 3,
    },
    tablet: {
        breakpoint: { max: 1048, min: 576 },
        items: 2,
        slidesToSlide: 2,
    },
    mobile: {
        breakpoint: { max: 576, min: 0 },
        items: 1,
        slidesToSlide: 1,
    }
}