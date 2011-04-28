<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/">
    <html>
      <head>
	<title>TamagoCheck Report Viewer</title>
	<link rel="stylesheet" href="http://webia.lip6.fr/~belhaouari/tamagoCheckReport.css" type="text/css" />
      </head>
      <body>			
	<xsl:apply-templates select="//tamagocheck" />
      </body>
    </html>
  </xsl:template>

  
  <xsl:template match="tamagocheck">
    <a name="start"><h1 class="information">
      <xsl:value-of select="@module" />.<xsl:value-of select="@contract" />
    </h1></a>

    <ul>
      <li><a href="#scenario">Scenario</a></li>
      <li><a href="#details">Details</a></li>
      <li>
	<ul>
	  <li><div class="nextstate">Transition reach next state: <xsl:value-of select="count(//nextstate)" /></div></li>
	  <li><div class="fixpoint">Transition reach cycle: <xsl:value-of select="count(//fixpoint)" /></div></li>
	  <li><div class="message">Transition message problem: <xsl:value-of select="count(//message)" /></div></li>
	</ul>
      </li>
    </ul>
    
    <div class="scenario">
      <a name="scenario"><h1>Scenario</h1></a>
      <xsl:apply-templates select="//openstate" />
      <a href="#start">top page</a>
    </div>
    
    <div class="details">
      <a name="details"><h1>Details</h1></a>
      <xsl:apply-templates select="//details" />
      <a href="#start">top page</a>
    </div>
  </xsl:template>
  
  <xsl:template match="openstate">
    <div class="openstate">
      <xsl:element name="a">
	<xsl:attribute name="name">state<xsl:value-of select="state/@id"/></xsl:attribute>
	<h3>State: <xsl:value-of select="@state" /></h3>
	<xsl:if test="state/parent-state/@id">
	<xsl:element name="a">
	  <xsl:attribute name="href">#state<xsl:value-of
	  select="state/parent-state/@id"/></xsl:attribute>
	  Go to parent state
	</xsl:element>
      </xsl:if>

	<h5>Domains (id=<xsl:value-of select="state/@id" />)</h5>
      </xsl:element>
      <table border="1" class="state">
      <tr><th class="titre">Name</th><th class="titre">Domain</th></tr>
      <xsl:for-each select="state/property">
	<tr>
	  <td class="nameprop">
	    <xsl:value-of select="@name" />
	  </td>
	  <td class="domainprop">
	    <xsl:value-of select="@value" />
	  </td>
	</tr>
      </xsl:for-each>
	 </table>
	 
      <xsl:if test="state/parent-state/@id">
	<xsl:element name="a">
	  <xsl:attribute name="href">#state<xsl:value-of
	  select="state/parent-state/@id"/></xsl:attribute>
	  Go to parent state
	</xsl:element>
      </xsl:if>

	 <div class="maxdepth">
		<xsl:apply-templates select="message" />
	 </div>

	 <h5>Transitions</h5>
	 <ol>
		<xsl:for-each select="transitions/transition">
		  <li>
			 <xsl:value-of select="@form" />
			 <ul>
				<xsl:for-each select="dnf">
				  <li>
					 <xsl:value-of select="@expr" />
					 <xsl:apply-templates select="nextstate" />
					 <xsl:apply-templates select="fixpoint" />
					 <xsl:apply-templates select="message" />
				  </li>
				</xsl:for-each>
			 </ul>
		  </li>	
		</xsl:for-each>
	 </ol>
	 <hr />
	 <a href="#start">top page</a>
	 <hr />
    </div>
  </xsl:template>
  
  <xsl:template match="nextstate">
    <br />
    <div class="nextstate">
      <xsl:element name="a">
	<xsl:attribute name="href">#state<xsl:value-of select="state/@id"/></xsl:attribute>
	<xsl:apply-templates select="state" />
      </xsl:element>
    </div>
  </xsl:template>

  <xsl:template match="fixpoint">
    <br />
    <div class="fixpoint">
      <ul>
	<xsl:for-each select="state">
	  <li>
	    <xsl:apply-templates select="." />
	  </li>
	</xsl:for-each>
      </ul>
    </div>
  </xsl:template>
  
  <xsl:template match="message">
    <br />
    <div class="message">
      <b><xsl:value-of select="." /></b>
    </div>
  </xsl:template>

  <xsl:template match="state">
    <div class="state">
      <div class="state_ident">States <b><xsl:value-of select="@cur" /></b> id=<xsl:value-of select="@id" /></div>
      <span class="previousstate">
	Parents: <xsl:value-of select="./parent-state/@id" />
      </span>
      <table class="state_domain" border="1">
	<tr><th class="titre">Name</th><th class="titre">Domain</th></tr>
	<xsl:for-each select="property">
	  <tr>
	    <td class="nameprop">
	      <xsl:value-of select="@name" />
	    </td>
	    <td class="domainprop">
	      <xsl:value-of select="@value" />
	    </td>
	  </tr>
	</xsl:for-each>
      </table>
    </div>
  </xsl:template>

</xsl:stylesheet>