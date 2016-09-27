package de.koegler85.model;

/**
 * @author Gabriel Kögler
 */
public enum SectionType
{
    STANDING,
    ORCHESTRA,
    LOGE,
    GALLERY;


    @Override
    public String toString ()
    {
        return this.name ();
    }
}
