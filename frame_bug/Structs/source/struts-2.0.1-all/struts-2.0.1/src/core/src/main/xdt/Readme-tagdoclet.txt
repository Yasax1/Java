Struts Custom Tagdoclet
================================

The provided xdoclet templates are based on original xdoclet distribution templates for tld generation.

The annotations may be used for classes extending TagSupport or oa.struts2.components.Component. If used in component
classes, you will also need a TagSupport wrapper class to generate valid tld (see @a2:tag#tld-tag-class).

Following a description of the provided tags and their parameters:

@a2.tag (Class javadoc)
-----------------------
Mark annotated class as end user tag provider

Parameters       | Required/Default   | Description
---------------------------------------------------------------------------------------------------------
name             | true/-             | Tag name
tld-body-content | false/"JSP"        | body-content param for tld
tld-tag-class    | */current Class    | TagSupport class (*required if current class is not)
description      | false/""           | Tag description. If not provided, javadoc content is used.


@a2.tagattribute (Setter method javadoc)
----------------------------------------
Mark annotated setter as tag attribute, usable for end user tag provider class and each superclass

Parameters       | Required/Default   | Description
---------------------------------------------------------------------------------------------------------
required         | no/"false"         | Whether attribute is required
default          | no/""              | Default value if not supplied
type             | no/"String/Object" | DESCRIPTIVE Type, for documentation
tld-type         | no/""              | ACTUAL JAVA TYPE, if explicitely needed for <type> element in tld
description      | no/""              | Tag attribute description. If not provided, javadoc content is used.
