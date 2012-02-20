<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/">
    <html>
      <head>
	<title>TamagoCDL Viewer</title>
	<link rel="stylesheet" href="tamagoCDL.css" type="text/css" />
      </head>
      <body>			
	<xsl:apply-templates />
      </body>
    </html>
  </xsl:template>
  
  <xsl:template match="service">
    <h1 class="service">
      <xsl:value-of select="@module" />.<xsl:value-of select="@name" />
    </h1>
    
    <h2>Properties:</h2>
    <table class="properties" border="1">
      <tr>
	<th class="titrename">Name</th>
	<th class="titretype">Type</th>
	<th class="titreaccess">Acces</th>
      </tr>
      <xsl:apply-templates select="//property" />
    </table>
    
    <h2>Invariants:</h2>
    <table class="invariants" border="1">
      <xsl:apply-templates select="//invariant" />
    </table>
    
    <h2>Methods:</h2>
    <table class="methods" border="1">
      <thead>
	<tr>
	  <th>Name</th><th>Type</th><th>ID</th><th>Precondition</th><th>Postcondition</th>
	</tr>
      </thead>
      <tbody>
      <xsl:apply-templates select="//method" />
      </tbody>
    </table>
  </xsl:template>
  
  <xsl:template match="property">
    <tr>
      <td class="name">
	<xsl:value-of select="@name" />
	</td><td class="type">
	<xsl:value-of select="@type" />
	</td><td  class="access">
	<xsl:value-of select="@access" />
      </td>
    </tr>
  </xsl:template>
  
  <xsl:template match="invariant">
    <tr>
      <td class="expression">
	<xsl:apply-templates />
      </td>
    </tr>
  </xsl:template>
  
  <xsl:template match="method">
    <tr>
      <td class="name">
	<xsl:value-of select="@name" />
	</td><td class="type">
	<xsl:value-of select="@rettype" />
	</td><td class="ident">
	<xsl:value-of select="@id" />
	</td><td class="preexpr">
	<xsl:apply-templates select="//pre" />
	</td><td class="postexpr">		
	<xsl:apply-templates select="post" />
      </td>
    </tr>
  </xsl:template>
  
  <xsl:template match="operator">
    <xsl:variable name="operateur">
      <xsl:value-of select="@name" />
    </xsl:variable>
    (
    <xsl:for-each select="*">
      <xsl:apply-templates select="." />
      
      <xsl:if test="position() != last()">
	<xsl:choose>
	  <xsl:when test="$operateur = 'eq'"><span class="exproperateur">=</span></xsl:when>
	  <xsl:when test="$operateur = 'ne'"><span class="exproperateur">!=</span></xsl:when>
	  <xsl:when test="$operateur = 'lt'"><span class="exproperateur"><xsl:text>&lt;</xsl:text></span></xsl:when>
	  <xsl:when test="$operateur = 'le'"><span class="exproperateur"><xsl:text>&lt;=</xsl:text></span></xsl:when>
	  <xsl:when test="$operateur = 'gt'"><span class="exproperateur"><xsl:text>&gt;</xsl:text></span></xsl:when>
	  <xsl:when test="$operateur = 'ge'"><span class="exproperateur"><xsl:text>&gt;=</xsl:text></span></xsl:when>
	  <xsl:when test="$operateur = 'add'"><span class="exproperateur">+</span></xsl:when>
	  <xsl:when test="$operateur = 'sub'"><span class="exproperateur">-</span></xsl:when>
	  <xsl:when test="$operateur = 'mul'"><span class="exproperateur">*</span></xsl:when>
	  <xsl:when test="$operateur = 'quo'"><span class="exproperateur">%/</span></xsl:when>
	  <xsl:when test="$operateur = 'div'"><span class="exproperateur">/</span></xsl:when>
	  <xsl:when test="$operateur = 'and'"><span class="exproperateur">and</span></xsl:when>
	  <xsl:when test="$operateur = 'or'"><span class="exproperateur">or</span></xsl:when>
	  <xsl:otherwise> <span class="exproperateur"><xsl:value-of select="@name" /></span></xsl:otherwise>
	</xsl:choose>
      </xsl:if>
    </xsl:for-each>
    )
  </xsl:template>
  
  <xsl:template match="read">
    <xsl:value-of select="@property" />
  </xsl:template>
  
  <xsl:template match="not">
    !<xsl:apply-templates />    
  </xsl:template>
  
  <xsl:template match="variable">
    <xsl:value-of select="@name" />
  </xsl:template>
  
  <xsl:template match="return">
    <span class="exprreturn">$return </span> 
  </xsl:template>
  
  <xsl:template match="int">
    <span class="exprvalue"><xsl:value-of select="@value" /></span> 
  </xsl:template>
  <xsl:template match="real">
    <span class="exprvalue"><xsl:value-of select="@value" /></span> 
  </xsl:template>
  <xsl:template match="boolean">
    <span class="exprvalue"><xsl:value-of select="@value" /></span> 
  </xsl:template>
  
  <xsl:template match="atpre">
    <xsl:apply-templates /><span class="expratpre">@atpre</span>
  </xsl:template>
  
  <xsl:template match="call">
    <span class="exprcall"><xsl:value-of select="@name" /></span>
    (
    <xsl:for-each select="*">
      <xsl:apply-templates select="." />
      
      <xsl:if test="position() != last()">
	<span class="exprcallsep">, </span>
      </xsl:if>
    </xsl:for-each>
    )
  </xsl:template>
  
  <xsl:template match="in">
    <span class="exprin"><xsl:value-of select="@label" />::</span>
    <xsl:apply-templates />
  </xsl:template>
  
</xsl:stylesheet>