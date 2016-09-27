package de.koegler85.model;

import javax.persistence.Embeddable;

/**
 * Created by koegler on 27.09.16.
 */
@Embeddable
public class Sectioning
{
    /*
        information for sectioning
     */
    private final Short NUMBER_OF_SECTIONS_IN_GALLERY;
    private final Short NUMBER_OF_SECTIONS_IN_BASEMENT;
    private final Short NUMBER_OF_ROWS_PER_SECTION;
    private final Short NUMBER_OF_SEATS_PER_ROW;

    public Sectioning (
            Short NUMBER_OF_SECTIONS_IN_GALLERY,
            Short NUMBER_OF_SECTIONS_IN_BASEMENT,
            Short NUMBER_OF_ROWS_PER_SECTION,
            Short NUMBER_OF_SEATS_PER_ROW )
    {
        this.NUMBER_OF_SECTIONS_IN_GALLERY = NUMBER_OF_SECTIONS_IN_GALLERY;
        this.NUMBER_OF_SECTIONS_IN_BASEMENT = NUMBER_OF_SECTIONS_IN_BASEMENT;
        this.NUMBER_OF_ROWS_PER_SECTION = NUMBER_OF_ROWS_PER_SECTION;
        this.NUMBER_OF_SEATS_PER_ROW = NUMBER_OF_SEATS_PER_ROW;
    }
}
