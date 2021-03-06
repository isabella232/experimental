/*
 * Copyright 2001 (C) MetaStuff, Ltd. All Rights Reserved.
 * 
 * This software is open source. 
 * See the bottom of this file for the licence.
 * 
 * $Id: ElementDecl.java,v 1.1.1.1 2001/01/16 18:00:01 jstrachan Exp $
 */

package org.dom4j.tool.dtd;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** <p><code>ElementDecl</code> represents the element declaration model
  * declaration taken from a DTD.</p>
  *
  *
  * @author <a href="mailto:james.strachan@metastuff.com">James Strachan</a>
  * @version $Revision: 1.1.1.1 $
  */
public class ElementDecl {

    /** The name of the element. */
    private String name;
    /** The model of the element taken from the SAX {@link org.xml.sax.ext.DeclHandler} class. */
    private String model;
    /** The attribute declarations */
    private ArrayList attributeList = new ArrayList();
    /** The name map of the attribute declarations */
    private HashMap attributeMap = new HashMap();
    
    public ElementDecl() {
    }
    
    public ElementDecl(String name, String model) {
        this.name = name;
        this.model = model;
    }

    /** @return the name of the element
      */
    public String getName() {
        return name;
    }
    
    /** Sets the name of the element
      * @param name New name value 
      */
    public void setName(String name) {
        this.name = name;
    }
    
    /** @return the model of the element
      */
    public String getModel() {
        return model;
    }
    
    /** Sets the model of the element
      * @param model New model value 
      */
    public void setModel(String model) {
        this.model = model;
    }

    
    /** Adds the given {@link AttributeDecl} object.
      * 
      * @param attributeDecl the declaration to be added
      */
    public void add(AttributeDecl attributeDecl) {
        attributeList.add(attributeDecl);
        attributeMap.put(attributeDecl.getAttributeName(), attributeDecl);
        attributeDecl.setElementDecl(this);        
    }
    
    /** Removes the given {@link AttributeDecl} object.
      * 
      * @param attributeDecl the declaration to be removed
      */
    public void remove(AttributeDecl attributeDecl) {
        attributeList.remove(attributeDecl);
        attributeMap.remove(attributeDecl.getAttributeName());
        attributeDecl.setElementDecl(null);        
    }
    
    /** Removes all {@link AttributeDecl} objects 
      */
    public void clear() {
        attributeList.clear();
        attributeMap.clear();
    }
    
    /** @return an iterator across all {@link AttributeDecl} instances
      */
    public Iterator iterator() {
        return attributeList.iterator();
    }
    
    /** @return the {@link AttributeDecl} instance for the given name or null
      */
    public AttributeDecl get(String name) {
        return (AttributeDecl) attributeMap.get(name);
    }
    
    /** Writes the state of the model to the given writer
      * 
      * @param out is the writer to output to
      */
    public void write( PrintWriter out ) {
        out.println( "<!ELEMENT " + getName() + " " + getModel() + ">" );
        if ( ! attributeList.isEmpty() ) {
            out.println( "<!ATTLIST " + getName() );
            for ( Iterator iter = iterator(); iter.hasNext(); ) {
                AttributeDecl attributeDecl = (AttributeDecl) iter.next();
                attributeDecl.write(out);
            }
            out.println( "  >" );
        }
    }
    
    /** @return true if this attribute is declared in a namespace 
     */
    public boolean hasNamespace() {
        int idx = getName().indexOf(':');
        return idx >= 0;
    }
    
    public String getNamespacePrefix() {
        String name = getName();
        int idx = name.indexOf(':');
        return ( idx >= 0 ) ? name.substring(0, idx) : "";
    }
}




/*
 * Redistribution and use of this software and associated documentation
 * ("Software"), with or without modification, are permitted provided
 * that the following conditions are met:
 *
 * 1. Redistributions of source code must retain copyright
 *    statements and notices.  Redistributions must also contain a
 *    copy of this document.
 *
 * 2. Redistributions in binary form must reproduce the
 *    above copyright notice, this list of conditions and the
 *    following disclaimer in the documentation and/or other
 *    materials provided with the distribution.
 *
 * 3. The name "DOM4J" must not be used to endorse or promote
 *    products derived from this Software without prior written
 *    permission of MetaStuff, Ltd.  For written permission,
 *    please contact dom4j-info@metastuff.com.
 *
 * 4. Products derived from this Software may not be called "DOM4J"
 *    nor may "DOM4J" appear in their names without prior written
 *    permission of MetaStuff, Ltd. DOM4J is a registered
 *    trademark of MetaStuff, Ltd.
 *
 * 5. Due credit should be given to the DOM4J Project
 *    (http://dom4j.org/).
 *
 * THIS SOFTWARE IS PROVIDED BY METASTUFF, LTD. AND CONTRIBUTORS
 * ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL
 * METASTUFF, LTD. OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Copyright 2001 (C) MetaStuff, Ltd. All Rights Reserved.
 *
 * $Id: ElementDecl.java,v 1.1.1.1 2001/01/16 18:00:01 jstrachan Exp $
 */
