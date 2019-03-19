#define GLEW_STATIC

#include <iostream>
#include "glm/glm.hpp"//core glm functionality
#include "glm/gtc/matrix_transform.hpp"//glm extension for generating common transformation matrices
#include "glm/gtc/matrix_inverse.hpp"
#include "glm/gtc/type_ptr.hpp"
#include "GLEW/glew.h"
#include "GLFW/glfw3.h"
#include <string>
#include "Shader.hpp"
#include "Camera.hpp"
#include "SkyBox.hpp"
#define PI 3.141592652

#define TINYOBJLOADER_IMPLEMENTATION

#include "Model3D.hpp"
#include "Mesh.hpp"

int glWindowWidth = 800;
int glWindowHeight = 600;
int retina_width, retina_height;
GLFWwindow* glWindow = NULL;

const GLuint SHADOW_WIDTH = 10000, SHADOW_HEIGHT = 10000;

glm::mat4 model;
GLuint modelLoc;
glm::mat4 view;
GLuint viewLoc;
glm::mat4 projection;
GLuint projectionLoc;
glm::mat3 normalMatrix;
GLuint normalMatrixLoc;
glm::mat3 lightDirMatrix;
GLuint lightDirMatrixLoc;

GLint lightType = 1;
GLint lightTypeLoc;

glm::vec3 lightDir;
GLuint lightDirLoc;
glm::vec3 lightDir2;
GLuint lightDirLoc2;
glm::vec3 lightColor;
GLuint lightColorLoc;
glm::vec3 initialShip = glm::vec3(3.0f, -1.0f, 5.0f);
glm::vec3 birdPos = glm::vec3(2.0f, -0.4f, 1.0f);
glm::vec3 duckPos = glm::vec3(7.0f, -0.95f, 2.5f);
glm::vec3 stonePos = glm::vec3(25.0f, 1.2f, 60.0f); 
glm::vec3 animPos = glm::vec3(8.5f, -0.3f, 7.0f);

gps::Camera myCamera(glm::vec3(3.2f, -0.6f, 6.5f), glm::vec3(0.0f, 0.0f, 0.0f));
GLfloat cameraSpeed = 0.1f;

bool pressedKeys[1024];
GLfloat angle;
GLfloat xmove, ymove;
GLfloat xactual, yactual;
GLfloat shipAngle=glm::radians(-90.0f);
GLfloat birdAngle = glm::radians(-180.0f);
GLfloat lightAngle;
GLfloat lightAngle1;
GLenum viewMode = GL_FILL;
float fogDensity = 0.01;
GLfloat fogLocation;

gps::Model3D myModel;
gps::Model3D wall;
gps::Model3D frog;
gps::Model3D ground;
gps::Model3D ground1;
gps::Model3D bridge;
gps::Model3D boat;
gps::Model3D tree;
gps::Model3D lamp;
gps::Model3D bird;
gps::Model3D duck;
gps::Model3D tower;
gps::Model3D stone;
gps::Model3D lightCube;
gps::Shader myCustomShader;
gps::Shader lightShader;
gps::Shader depthMapShader;

GLuint shadowMapFBO;
GLuint depthMapTexture;
GLuint textureID;

std::vector<const GLchar*> faces;
gps::SkyBox mySkyBox;
gps::Shader skyboxShader;

bool firstMouse = true;
float lastX = retina_width / 2.0f;
float lastY = retina_height / 2.0f;

float currentFrame = 0.0f;
float deltaTime = 0.0f;	// Time between current frame and last frame
float lastFrame = glfwGetTime();

float duckAngle = 0.0f;
float duckMove = 0.1f;
float birdMove = 0.1f;
float cameraMove = 0.1f;

float animAngle = glm::radians(-180.0f);

int anim = 0;

bool firstRender = true;

float xoffset, yoffset;
float yaw = -90.0;
float pitch = 0.0;

int leftside = 1;
int rightside = 1;
int upside = 1;
int downside = 1;
int initialcall = 1;


GLenum glCheckError_(const char *file, int line)
{
	GLenum errorCode;
	while ((errorCode = glGetError()) != GL_NO_ERROR)
	{
		std::string error;
		switch (errorCode)
		{
		case GL_INVALID_ENUM:                  error = "INVALID_ENUM"; break;
		case GL_INVALID_VALUE:                 error = "INVALID_VALUE"; break;
		case GL_INVALID_OPERATION:             error = "INVALID_OPERATION"; break;
		case GL_STACK_OVERFLOW:                error = "STACK_OVERFLOW"; break;
		case GL_STACK_UNDERFLOW:               error = "STACK_UNDERFLOW"; break;
		case GL_OUT_OF_MEMORY:                 error = "OUT_OF_MEMORY"; break;
		case GL_INVALID_FRAMEBUFFER_OPERATION: error = "INVALID_FRAMEBUFFER_OPERATION"; break;
		}
		std::cout << error << " | " << file << " (" << line << ")" << std::endl;
	}
	return errorCode;
}
#define glCheckError() glCheckError_(__FILE__, __LINE__)

void windowResizeCallback(GLFWwindow* window, int width, int height)
{
	fprintf(stdout, "window resized to width: %d , and height: %d\n", width, height);

	glfwGetFramebufferSize(glWindow, &retina_width, &retina_height);

	myCustomShader.useShaderProgram();

	//set projection matrix
	glm::mat4 projection = glm::perspective(glm::radians(45.0f), (float)retina_width / (float)retina_height, 0.1f, 1000.0f);
	//send matrix data to shader
	GLint projLoc = glGetUniformLocation(myCustomShader.shaderProgram, "projection");
	glUniformMatrix4fv(projLoc, 1, GL_FALSE, glm::value_ptr(projection));
	
	lightShader.useShaderProgram();
	
	glUniformMatrix4fv(glGetUniformLocation(lightShader.shaderProgram, "projection"), 1, GL_FALSE, glm::value_ptr(projection));

	//set Viewport transform
	glViewport(0, 0, retina_width, retina_height);
}

void keyboardCallback(GLFWwindow* window, int key, int scancode, int action, int mode)
{
	if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS)
		glfwSetWindowShouldClose(window, GL_TRUE);

	if (key >= 0 && key < 1024)
	{
		if (action == GLFW_PRESS)
			pressedKeys[key] = true;
		else if (action == GLFW_RELEASE)
			pressedKeys[key] = false;
	}
}


void mouse_callback(GLFWwindow* window, double xpos, double ypos)
{

	printf("%f %f\n", xpos, ypos);
	if (firstMouse)
	{
		lastX = xpos;
		lastY = ypos;
		firstMouse = false;
	}

	float xoffset = xpos - lastX;
	float yoffset = lastY - ypos;
	
	lastX = xpos;
	lastY = ypos;

	float sensitivity = 0.05;
	xoffset *= sensitivity;
	yoffset *= sensitivity;

	yaw += xoffset;
	pitch += yoffset;

	if (pitch > 89.0f)
		pitch = 89.0f;
	if (pitch < -89.0f)
		pitch = -89.0f;

	myCamera.rotate(pitch, yaw);

}

void scroll_callback(GLFWwindow* window, double xoffset, double yoffset)
{	
	cameraSpeed = 5 * deltaTime;

	if (yoffset > 0)
		myCamera.move(gps::MOVE_FORWARD, cameraSpeed);
	else
		myCamera.move(gps::MOVE_BACKWARD, cameraSpeed);

}

void followShip() {

	glm::vec3 newPosition = initialShip;
	
	newPosition.x -= 0.2f*sin(shipAngle) - 1.5f*cos(shipAngle);
	newPosition.y += 0.4f;
	newPosition.z -= 1.5f*sin(shipAngle) + 0.2f*cos(shipAngle);
	myCamera.set(newPosition);

	myCamera.rotate(pitch, -180 - shipAngle * 180 / PI);
}

bool checkForCollision(glm::vec3 ship) {

	//collision with beach
	if(ship.x > 5.7f)
		return true;

	//collision with wall left
	if (ship.x < -7.4f)
		return true;

	//collision with wall backward
	if (ship.z > 7.4f)
		return true;

	//collision with wall forward
	if (ship.z < -7.4f)
		return true;

	//collision with boat
	if (ship.x > 5.1f && ship.z > 1.15f && ship.z < 1.85f)
		return true;

	//collision with bridge
	if (ship.x > 5.3f && ship.z > 1.16f && ship.z < 1.8f)
		return true;

	//collision with stone on the beach
	if (ship.x > 5.6f && ship.z > -0.33f && ship.z < 0.11f)
		return true;

	//collision with stone near the starting position
	if (ship.x > 1.5f && ship.x < 2.2f && ship.z > 3.3f && ship.z < 4.3f)
		return true;

	//collision with group of three stones
	if (ship.x > -6.5f && ship.x < -5.46f && ship.z > 0.45f && ship.z < 2.4f)
		return true;

	return false;
}

void presentation() {

	myCamera.set(animPos);

	if (initialcall) {
		myCamera.rotate(0.0f, -180.0f);
		initialcall = 0;
	}

	if (leftside) {
		if (animPos.z > -6.5f)
			animPos.z -= 1.0f * deltaTime;
		else if (animPos.z <= -6.5f) {
			myCamera.rotate(pitch, -270.0f);
			leftside = 0;
		}
	}
	
	if (leftside == 0  && upside) {
		if (animPos.x > -6.5f)
			animPos.x -= 1.0f * deltaTime;
		else if (animPos.x <= -6.5f) {
			myCamera.rotate(pitch, 0.0f);
			upside = 0;
		}
	}

	if (leftside ==0 && upside ==0 && rightside) {
		if (animPos.z < 6.5f)
			animPos.z += 1.0f * deltaTime;
		else if (animPos.z >= 6.5f) {
			myCamera.rotate(pitch, -90.0f);
			rightside = 0;
		}
	}

	if (leftside == 0 && upside == 0 && rightside == 0 && downside) {
		if (animPos.x < 7.5f)
			animPos.x += 1.0f * deltaTime;
		else if (animPos.x >= 7.5f) {
			myCamera.rotate(pitch, -110.0f);
			downside = 0;
		}
	} 

	if (upside == 0 && downside == 0 && leftside == 0 && rightside == 0)
		anim = 0;

}

void processMovement()
{

	cameraSpeed = 5*deltaTime;
	

	if (pressedKeys[GLFW_KEY_T]) {
		followShip();
	}

	if (pressedKeys[GLFW_KEY_R]) {
		anim = 1;
	}

	if (pressedKeys[GLFW_KEY_Z]) {
		anim = 0;
	}

	if (pressedKeys[GLFW_KEY_UP]) {
		ymove=1.0f * deltaTime;

		if (pressedKeys[GLFW_KEY_LEFT]) {
			xmove = -1.0f * deltaTime;
			followShip();

		}
		else if (pressedKeys[GLFW_KEY_RIGHT]) {
			xmove = 1.0f * deltaTime;
			followShip();

		}
		else{
			followShip();
		}

		
	}

	if (pressedKeys[GLFW_KEY_DOWN]) {
		ymove=-1.0f * deltaTime;

		if (pressedKeys[GLFW_KEY_LEFT]) {
			xmove = 1.0f * deltaTime;
			followShip();

		}

		else if (pressedKeys[GLFW_KEY_RIGHT]) {
			xmove = -1.0f * deltaTime;
			followShip();

		}
		else {
			followShip();
		}

	}


	if (pressedKeys[GLFW_KEY_F]) {
			viewMode = GL_LINE;
	}

	if (pressedKeys[GLFW_KEY_G]) {
		viewMode = GL_FILL;
	}

	if (pressedKeys[GLFW_KEY_H]) {
		viewMode = GL_POINT;
	}


	glPolygonMode(GL_FRONT_AND_BACK, viewMode);

	if (pressedKeys[GLFW_KEY_W]) {
		myCamera.move(gps::MOVE_FORWARD, cameraSpeed);

	}

	if (pressedKeys[GLFW_KEY_S]) {
		myCamera.move(gps::MOVE_BACKWARD, cameraSpeed);
	}

	if (pressedKeys[GLFW_KEY_A]) {
		myCamera.move(gps::MOVE_LEFT, cameraSpeed);
	}

	if (pressedKeys[GLFW_KEY_D]) {
		myCamera.move(gps::MOVE_RIGHT, cameraSpeed);
	}

	if (pressedKeys[GLFW_KEY_J]) {

		lightAngle += 100.0f * deltaTime;
		if (lightAngle > 360.0f)
			lightAngle -= 360.0f;
		glm::vec3 lightDirTr = glm::vec3(glm::rotate(glm::mat4(1.0f), glm::radians(lightAngle), glm::vec3(0.0f, 1.0f, 0.0f)) * glm::vec4(lightDir, 1.0f));
		myCustomShader.useShaderProgram();
		glUniform3fv(lightDirLoc, 1, glm::value_ptr(lightDirTr));
	}

	if (pressedKeys[GLFW_KEY_L]) {
		lightAngle -= 100.0 * deltaTime;
		if (lightAngle < 0.0f)
			lightAngle += 360.0f;
		glm::vec3 lightDirTr = glm::vec3(glm::rotate(glm::mat4(1.0f), glm::radians(lightAngle), glm::vec3(0.0f, 1.0f, 0.0f)) * glm::vec4(lightDir, 1.0f));
		myCustomShader.useShaderProgram();
		glUniform3fv(lightDirLoc, 1, glm::value_ptr(lightDirTr));
	}	

	if (pressedKeys[GLFW_KEY_I]) {

		lightAngle1 += 100.0f * deltaTime;
		if (lightAngle1 > 360.0f)
			lightAngle1 -= 360.0f;
		glm::vec3 lightDirTr = glm::vec3(glm::rotate(glm::mat4(1.0f), glm::radians(lightAngle1), glm::vec3(0.0f, 0.0f, 1.0f)) * glm::vec4(lightDir, 1.0f));
		myCustomShader.useShaderProgram();
		glUniform3fv(lightDirLoc, 1, glm::value_ptr(lightDirTr));
	}

	if (pressedKeys[GLFW_KEY_P]) {
		lightAngle1 -= 100.0 * deltaTime;
		if (lightAngle1 < 0.0f)
			lightAngle1 += 360.0f;
		glm::vec3 lightDirTr = glm::vec3(glm::rotate(glm::mat4(1.0f), glm::radians(lightAngle1), glm::vec3(0.0f, 0.0f, 1.0f)) * glm::vec4(lightDir, 1.0f));
		myCustomShader.useShaderProgram();
		glUniform3fv(lightDirLoc, 1, glm::value_ptr(lightDirTr));
	}


	if (pressedKeys[GLFW_KEY_B]) {

		fogDensity += deltaTime/30;
		myCustomShader.useShaderProgram();
		fogLocation = glGetUniformLocation(myCustomShader.shaderProgram, "fogDensity");
		glUniform1f(fogLocation, fogDensity);
	}

	if (pressedKeys[GLFW_KEY_N]) {

		if(fogDensity)
			fogDensity -= deltaTime/30;
		myCustomShader.useShaderProgram();
		fogLocation = glGetUniformLocation(myCustomShader.shaderProgram, "fogDensity");
		glUniform1f(fogLocation, fogDensity);
	}

	xactual = -ymove*cos(shipAngle);
	yactual = ymove*sin(shipAngle);

	if (!checkForCollision(initialShip + glm::vec3(xactual, 0.0f, yactual))) {

		initialShip = initialShip + glm::vec3(xactual, 0.0f, yactual);

		//compute actual ship angle
		shipAngle += glm::radians(-xmove * 15);
		if ((shipAngle + glm::radians(-xmove * 15)) < glm::radians(-180.0f))
		{
			shipAngle = glm::radians(360.0f) - shipAngle;
		}
		if ((shipAngle + glm::radians(-xmove * 15)) > glm::radians(180.0f))
		{
			shipAngle = shipAngle - glm::radians(360.0f);
		}

	}
	xmove = ymove = 0.0f;


	//compute duck position
	duckPos = duckPos + glm::vec3(0.0f, 0.0f, duckMove * deltaTime);
	if (duckPos.z >= 4.0f) {
		duckMove = -duckMove;
		duckAngle = 180.0f;
	}
	if (duckPos.z <= 2.3f) {
		duckMove = -duckMove;
		duckAngle = 0.0f;
	}

	birdAngle += 1.0f * deltaTime;

	birdPos = birdPos + glm::vec3(4*birdMove*deltaTime*sin(birdAngle), 0.0f, 4*birdMove*deltaTime*cos(birdAngle));

	if (anim == 1) {
		presentation();
	}

}


bool initOpenGLWindow()
{
	if (!glfwInit()) {
		fprintf(stderr, "ERROR: could not start GLFW3\n");
		return false;
	}

	//for Mac OS X
	glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
	glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1);
	glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
	glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);


	glWindow = glfwCreateWindow(glWindowWidth, glWindowHeight, "OpenGL Shader Example", NULL, NULL);
	if (!glWindow) {
		fprintf(stderr, "ERROR: could not open window with GLFW3\n");
		glfwTerminate();
		return false;
	}

	glfwSetWindowSizeCallback(glWindow, windowResizeCallback);
	glfwMakeContextCurrent(glWindow);
	

	glfwWindowHint(GLFW_SAMPLES, 4);

	// start GLEW extension handler
	glewExperimental = GL_TRUE;
	glewInit();

	// get version info
	const GLubyte* renderer = glGetString(GL_RENDERER); // get renderer string
	const GLubyte* version = glGetString(GL_VERSION); // version as a string
	printf("Renderer: %s\n", renderer);
	printf("OpenGL version supported %s\n", version);

	//for RETINA display
	glfwGetFramebufferSize(glWindow, &retina_width, &retina_height);

	glfwSetKeyCallback(glWindow, keyboardCallback);
	glfwSetCursorPosCallback(glWindow, mouse_callback);
    glfwSetInputMode(glWindow, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
	glfwSetScrollCallback(glWindow, scroll_callback);
	return true;
}

void initOpenGLState()
{
	glClearColor(0.3f, 0.3f, 0.3f, 1.0f);
	glViewport(0, 0, retina_width, retina_height);

	glEnable(GL_DEPTH_TEST); // enable depth-testing
	glDepthFunc(GL_LESS); // depth-testing interprets a smaller value as "closer"
	glCullFace(GL_BACK); // cull back face
	glFrontFace(GL_CCW); // GL_CCW for counter clock-wise
	glPolygonMode(GL_FRONT_AND_BACK, viewMode);
}

void initFBOs()
{
	//generate FBO ID
	glGenFramebuffers(1, &shadowMapFBO);

	//create depth texture for FBO
	glGenTextures(1, &depthMapTexture);
	glBindTexture(GL_TEXTURE_2D, depthMapTexture);
	glTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH_COMPONENT,
		SHADOW_WIDTH, SHADOW_HEIGHT, 0, GL_DEPTH_COMPONENT, GL_FLOAT, NULL);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);

	//attach texture to FBO
	glBindFramebuffer(GL_FRAMEBUFFER, shadowMapFBO);
	glFramebufferTexture2D(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_TEXTURE_2D, depthMapTexture, 0);
	glDrawBuffer(GL_NONE);
	glReadBuffer(GL_NONE);
	glBindFramebuffer(GL_FRAMEBUFFER, 0);

	glGenTextures(1, &textureID);
	glBindTexture(GL_TEXTURE_CUBE_MAP, textureID);

	glEnable(GL_BLEND);
	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
}

glm::mat4 computeLightSpaceTrMatrix()
{
	const GLfloat near_plane = 1.0f, far_plane = 20.0f;
	glm::mat4 lightProjection = glm::ortho(-40.0f, 40.0f, -40.0f, 40.0f, near_plane, far_plane);

	glm::vec3 lightDirTr = glm::vec3(glm::rotate(glm::mat4(1.0f), glm::radians(lightAngle), glm::vec3(0.0f, 1.0f, 0.0f)) * glm::vec4(lightDir, 1.0f));
	glm::mat4 lightView = glm::lookAt(lightDirTr, myCamera.getCameraTarget(), glm::vec3(0.0f, 1.0f, 0.0f));

	return lightProjection * lightView;
}

void initModels()
{
	myModel = gps::Model3D("objects/vapor/Cruisership2012/Cruiser2012.obj", "objects/vapor/Cruisership2012");	
	ground = gps::Model3D("objects/ocean/Ocean.obj", "objects/ocean/");
	ground1 = gps::Model3D("objects/beach/Ocean.obj", "objects/beach/");
	bridge = gps::Model3D("objects/bridge/WoodenBridge_01.obj", "objects/bridge/");
	boat = gps::Model3D("objects/boat/12219_boat_v2_L2.obj", "objects/boat/");
	stone = gps::Model3D("objects/stone/Rock_9.obj", "objects/stone/");
	wall = gps::Model3D("objects/ground/ground.obj", "objects/ground/");
	duck = gps::Model3D("objects/duck/10602_Rubber_Duck_v1_L3.obj", "objects/duck/");
	tower = gps::Model3D("objects/tower/wooden_watch_tower2.obj", "objects/tower/");
	frog = gps::Model3D("objects/frog/20436_Frog_v1_textured.obj", "objects/frog/");
	bird = gps::Model3D("objects/bird/hummingbird.obj", "objects/bird/");
	tree = gps::Model3D("objects/palmtree/10446_Palm_Tree_v1_max2010_iteration-2.obj", "objects/palmtree/");
	lamp = gps::Model3D("objects/lamp/honey_comb_lamp.obj", "objects/lamp/");
	lightCube = gps::Model3D("objects/cube/cube.obj", "objects/cube/");
}

void initShaders()
{
	faces.push_back("textures/daylight/Daylight Box_Right.bmp");
	faces.push_back("textures/daylight/Daylight Box_Left.bmp");
	faces.push_back("textures/daylight/Daylight Box_Top.bmp");
	faces.push_back("textures/daylight/Daylight Box_Bottom.bmp");
	faces.push_back("textures/daylight/Daylight Box_Front.bmp");
	faces.push_back("textures/daylight/Daylight Box_Back.bmp");

	myCustomShader.loadShader("shaders/shaderStart.vert", "shaders/shaderStart.frag");
	lightShader.loadShader("shaders/lightCube.vert", "shaders/lightCube.frag");
	depthMapShader.loadShader("shaders/simpleDepthMap.vert", "shaders/simpleDepthMap.frag");

	mySkyBox.Load(faces);
	skyboxShader.loadShader("shaders/skyboxShader.vert", "shaders/skyboxShader.frag");

	skyboxShader.useShaderProgram();
	view = myCamera.getViewMatrix();
	glUniformMatrix4fv(glGetUniformLocation(skyboxShader.shaderProgram, "view"), 1, GL_FALSE,
		glm::value_ptr(view));

	projection = glm::perspective(glm::radians(45.0f), (float)retina_width / (float)retina_height, 0.1f, 1000.0f);
	glUniformMatrix4fv(glGetUniformLocation(skyboxShader.shaderProgram, "projection"), 1, GL_FALSE,
		glm::value_ptr(projection));

}

void initUniforms()
{
	myCustomShader.useShaderProgram();

	modelLoc = glGetUniformLocation(myCustomShader.shaderProgram, "model");

	viewLoc = glGetUniformLocation(myCustomShader.shaderProgram, "view");
	
	normalMatrixLoc = glGetUniformLocation(myCustomShader.shaderProgram, "normalMatrix");
	
	lightDirMatrixLoc  = glGetUniformLocation(myCustomShader.shaderProgram, "lightDirMatrix");
	
	fogLocation = glGetUniformLocation(myCustomShader.shaderProgram, "fogDensity");
	glUniform1f(fogLocation, 0.01f);

	projection = glm::perspective(glm::radians(45.0f), (float)retina_width / (float)retina_height, 0.1f, 1000.0f);
	projectionLoc = glGetUniformLocation(myCustomShader.shaderProgram, "projection");
	glUniformMatrix4fv(projectionLoc, 1, GL_FALSE, glm::value_ptr(projection));	

	//set the light direction (direction towards the light)
	lightDir = glm::vec3(7.0f, 9.0f, -7.0f);
	lightDirLoc = glGetUniformLocation(myCustomShader.shaderProgram, "lightDir");
	glUniform3fv(lightDirLoc, 1, glm::value_ptr(lightDir));

	lightDir2 = glm::vec3(5.75f, 0.0f, 1.75f);
	lightDirLoc2 = glGetUniformLocation(myCustomShader.shaderProgram, "lightDir2");
	glUniform3fv(lightDirLoc2, 1, glm::value_ptr(lightDir2));

	//set light color
	lightColor = glm::vec3(1.0f, 1.0f, 1.0f); //white light
	lightColorLoc = glGetUniformLocation(myCustomShader.shaderProgram, "lightColor");
	glUniform3fv(lightColorLoc, 1, glm::value_ptr(lightColor));

	lightShader.useShaderProgram();
	glUniformMatrix4fv(glGetUniformLocation(lightShader.shaderProgram, "projection"), 1, GL_FALSE, glm::value_ptr(projection));


}

void renderScene()
{
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	currentFrame = glfwGetTime();
	deltaTime = currentFrame - lastFrame;
	lastFrame = currentFrame;

	if (firstRender) {
		myCamera.rotate(0.0f, -90.0f);
		firstRender = false;
	}

	processMovement();

	//render the scene to the depth buffer (first pass)

	depthMapShader.useShaderProgram();

	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "lightSpaceTrMatrix"),
		1,
		GL_FALSE,
		glm::value_ptr(computeLightSpaceTrMatrix()));

	glViewport(0, 0, SHADOW_WIDTH, SHADOW_HEIGHT);
	glBindFramebuffer(GL_FRAMEBUFFER, shadowMapFBO);
	glClear(GL_DEPTH_BUFFER_BIT);

	//ship
	model = glm::translate(glm::mat4(1.0f), initialShip);
	model = glm::scale(model, glm::vec3(0.001f));
	model = glm::rotate(model, glm::radians(angle), glm::vec3(0, 1, 0));
	model = glm::rotate(model, shipAngle, glm::vec3(0, 1, 0));
	//send model matrix to shader
	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	myModel.Draw(depthMapShader);

	//ground
	model = glm::translate(glm::mat4(1.0f), glm::vec3(0.0f, -1.0f, 0.5f));
	model = glm::scale(model, glm::vec3(40.0f, 0.3f, 40.0f));
	//send model matrix to shader
	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	ground.Draw(depthMapShader);

	//ground1
	model = glm::translate(glm::mat4(1.0f), glm::vec3(8.0f, -0.96f, 0.0f));
	model = glm::scale(model, glm::vec3(1.0f, 1.2f, 4.0f));
	//send model matrix to shader
	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	ground1.Draw(depthMapShader);
	
	//bridge
	model = glm::translate(glm::mat4(1.0f), glm::vec3(6.2f, -0.95f, 1.7f));
	model = glm::scale(model, glm::vec3(0.002f, 0.002f, 0.004f));
	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	bridge.Draw(depthMapShader);

	//boat
	model = glm::translate(glm::mat4(1.0f), glm::vec3(5.5f, -1.0f, 1.7f));
	model = glm::scale(model, glm::vec3(0.0003f, 0.0003f, 0.0003f));
	model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(1.0f, 0.0f, 0.0f));
	model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(0.0f, 0.0f, 1.0f));

	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	boat.Draw(depthMapShader);

	//tree
	model = glm::translate(glm::mat4(1.0f), glm::vec3(7.8f, -1.0f, -5.0f));
	model = glm::scale(model, glm::vec3(0.0007f, 0.0007f, 0.0007f));
	model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(1.0f, 0.0f, 0.0f));
	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	tree.Draw(depthMapShader);
	
	//duck
	model = glm::translate(glm::mat4(1.0f), duckPos);
	model = glm::scale(model, glm::vec3(0.01f, 0.01f, 0.01f));
	model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(1.0f, 0.0f, 0.0f));
	model = glm::rotate(model, glm::radians(duckAngle), glm::vec3(0.0f, 0.0f, 1.0f));

	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	duck.Draw(depthMapShader);

	//tower
	model = glm::translate(glm::mat4(1.0f), glm::vec3(7.0f, -1.0f, 5.0f));
	model = glm::scale(model, glm::vec3(0.07f, 0.07f, 0.07f));

	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	tower.Draw(depthMapShader);

	//bird
	model = glm::translate(glm::mat4(1.0f), birdPos);
	model = glm::scale(model, glm::vec3(0.0001f, 0.0001f, 0.0001f));
	model = glm::rotate(model, birdAngle, glm::vec3(0.0f, 1.0f, 0.0f));

	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	bird.Draw(depthMapShader);

	//wall
	model = glm::translate(glm::mat4(1.0f), glm::vec3(11.0f, -0.5f, 0.0f));
	model = glm::scale(model, glm::vec3(0.2f, 0.07f, 1.1f));
	model = glm::rotate(model, glm::radians(45.0f), glm::vec3(0.0f, 0.0f, 1.0f));

	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	wall.Draw(depthMapShader);

	//wall1
	model = glm::translate(glm::mat4(1.0f), glm::vec3(-9.0f, -0.5f, 0.0f));
	model = glm::scale(model, glm::vec3(0.2f, 0.07f, 1.1f));
	model = glm::rotate(model, glm::radians(-45.0f), glm::vec3(0.0f, 0.0f, 1.0f));
	
	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	wall.Draw(depthMapShader);

	//wall2
	model = glm::translate(glm::mat4(1.0f), glm::vec3(1.0f, -0.5f, -9.0f));
	model = glm::scale(model, glm::vec3(1.2f, 0.07f, 0.2f));
	model = glm::rotate(model, glm::radians(45.0f), glm::vec3(1.0f, 0.0f, 0.0f));

	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	wall.Draw(depthMapShader);

	//wall3
	model = glm::translate(glm::mat4(1.0f), glm::vec3(1.0f, -0.5f, 9.0f));
	model = glm::scale(model, glm::vec3(1.2f, 0.07f, 0.2f));
	model = glm::rotate(model, glm::radians(-45.0f), glm::vec3(1.0f, 0.0f, 0.0f));

	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	wall.Draw(depthMapShader);

	//stone
	model = glm::translate(glm::mat4(1.0f), glm::vec3(6.0f, -0.9f, 0.1f));
	model = glm::scale(model, glm::vec3(0.001f, 0.0006f, 0.002f));
	model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(0.0f, 1.0f, 0.0f));

	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	stone.Draw(depthMapShader);

	//stone1
	model = glm::translate(glm::mat4(1.0f), glm::vec3(2.0f, -0.92f, 4.0f));
	model = glm::scale(model, glm::vec3(0.002f, 0.0012f, 0.004f));
	model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(0.0f, 1.0f, 0.0f));

	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	stone.Draw(depthMapShader);

	//stone2
	model = glm::translate(glm::mat4(1.0f), glm::vec3(-6.0f, -0.92f, 2.0f));
	model = glm::scale(model, glm::vec3(0.002f, 0.0012f, 0.004f));
	model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(0.0f, 1.0f, 0.0f));

	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	stone.Draw(depthMapShader);

	//stone3
	model = glm::translate(glm::mat4(1.0f), glm::vec3(-6.0f, -0.92f, 1.5f));
	model = glm::scale(model, glm::vec3(0.002f, 0.0012f, 0.004f));
	model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(0.0f, 1.0f, 0.0f));

	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	stone.Draw(depthMapShader);

	//stone4
	model = glm::translate(glm::mat4(1.0f), glm::vec3(-6.0f, -0.92f, 1.0f));
	model = glm::scale(model, glm::vec3(0.002f, 0.0012f, 0.004f));
	model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(0.0f, 1.0f, 0.0f));

	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	stone.Draw(depthMapShader);

	//frog
	model = glm::translate(glm::mat4(1.0f), glm::vec3(-6.0f, -0.78f, 2.0f));
	model = glm::scale(model, glm::vec3(0.005f, 0.003f, 0.01f));
	model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(1.0f, 0.0f, 0.0f));
	model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(0.0f, 0.0f, 1.0f));

	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	frog.Draw(depthMapShader);
	

	glBindFramebuffer(GL_FRAMEBUFFER, 0);

	//render the scene (second pass)

	myCustomShader.useShaderProgram();

	//send lightSpace matrix to shader
	glUniformMatrix4fv(glGetUniformLocation(myCustomShader.shaderProgram, "lightSpaceTrMatrix"),
		1,
		GL_FALSE,
		glm::value_ptr(computeLightSpaceTrMatrix()));

	//send view matrix to shader
	view = myCamera.getViewMatrix();
	glUniformMatrix4fv(glGetUniformLocation(myCustomShader.shaderProgram, "view"),
		1,
		GL_FALSE,
		glm::value_ptr(view));

	//compute light direction transformation matrix
	lightDirMatrix = glm::mat3(glm::inverseTranspose(view));
	//send lightDir matrix data to shader
	glUniformMatrix3fv(lightDirMatrixLoc, 1, GL_FALSE, glm::value_ptr(lightDirMatrix));

	glViewport(0, 0, retina_width, retina_height);
	myCustomShader.useShaderProgram();

	//bind the depth map
	glActiveTexture(GL_TEXTURE3);
	glBindTexture(GL_TEXTURE_2D, depthMapTexture);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "shadowMap"), 3);

	//create model matrix for ship
	model = glm::translate(glm::mat4(1.0f), initialShip);
	model = glm::scale(model, glm::vec3(0.001f));
	model = glm::rotate(model, glm::radians(angle), glm::vec3(0, 1, 0));
	model = glm::rotate(model, shipAngle, glm::vec3(0, 1, 0));

	//send model matrix data to shader	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//compute normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	myModel.Draw(myCustomShader);

	//create model matrix for ground
	model = glm::translate(glm::mat4(1.0f), glm::vec3(0.0f, -1.0f, 0.0f));
	model = glm::scale(model, glm::vec3(4.0f, 0.7f, 4.0f));

	//send model matrix data to shader
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//create normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	ground.Draw(myCustomShader);

	//create model matrix for ground1
	model = glm::translate(glm::mat4(1.0f), glm::vec3(8.0f, -0.96f, 0.0f));
	model = glm::scale(model, glm::vec3(1.0f, 1.2f, 4.0f));

	//send model matrix data to shader
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//create normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	ground1.Draw(myCustomShader);

	//create model matrix for bridge
	model = glm::translate(glm::mat4(1.0f), glm::vec3(6.2f, -0.95f, 1.7f));
	model = glm::scale(model, glm::vec3(0.002f, 0.002f, 0.004f));

	//send model matrix data to shader
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//create normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	bridge.Draw(myCustomShader);


	//create model matrix for boat
	model = glm::translate(glm::mat4(1.0f), glm::vec3(5.5f, -1.0f, 1.7f));
	model = glm::scale(model, glm::vec3(0.0003f, 0.0003f, 0.0003f));
	model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(1.0f, 0.0f, 0.0f));
	model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(0.0f, 0.0f, 1.0f));

	//send model matrix data to shader
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//create normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	boat.Draw(myCustomShader);

	//create model matrix for tree
	model = glm::translate(glm::mat4(1.0f), glm::vec3(7.8f, -1.0f, -5.0f));
	model = glm::scale(model, glm::vec3(0.0007f, 0.0007f, 0.0007f));
	model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(1.0f, 0.0f, 0.0f));
	//send model matrix data to shader
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//create normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	tree.Draw(myCustomShader);

	//create model matrix for duck
	model = glm::translate(glm::mat4(1.0f), duckPos);
	model = glm::scale(model, glm::vec3(0.01f, 0.01f, 0.01f));
	model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(1.0f, 0.0f, 0.0f));
	model = glm::rotate(model, glm::radians(duckAngle), glm::vec3(0.0f, 0.0f, 1.0f));

	//send model matrix data to shader
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//create normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	duck.Draw(myCustomShader);

	//create model matrix for tower
	model = glm::translate(glm::mat4(1.0f), glm::vec3(7.0f, -1.0f, 5.0f));
	model = glm::scale(model, glm::vec3(0.07f, 0.07f, 0.07f));
	//model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(1.0f, 0.0f, 0.0f));
	//send model matrix data to shader
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//create normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	tower.Draw(myCustomShader);

	//create model matrix for bird
	model = glm::translate(glm::mat4(1.0f), birdPos);
	model = glm::scale(model, glm::vec3(0.0001f, 0.0001f, 0.0001f));
	model = glm::rotate(model, birdAngle, glm::vec3(0.0f, 1.0f, 0.0f));
	//send model matrix data to shader
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//create normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	bird.Draw(myCustomShader);

	//create model matrix for wall
	model = glm::translate(glm::mat4(1.0f), glm::vec3(11.0f, -0.5f, 0.0f));
	model = glm::scale(model, glm::vec3(0.2f, 0.07f, 1.1f));
	model = glm::rotate(model, glm::radians(45.0f), glm::vec3(0.0f, 0.0f, 1.0f));
	//model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(1.0f, 0.0f, 0.0f));
	//send model matrix data to shader
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//create normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	wall.Draw(myCustomShader);

	//create model matrix for wall1
	model = glm::translate(glm::mat4(1.0f), glm::vec3(-9.0f, -0.5f, 0.0f));
	model = glm::scale(model, glm::vec3(0.2f, 0.07f, 1.1f));
	model = glm::rotate(model, glm::radians(-45.0f), glm::vec3(0.0f, 0.0f, 1.0f));
	//model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(1.0f, 0.0f, 0.0f));
	//send model matrix data to shader
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//create normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	wall.Draw(myCustomShader);

	//create model matrix for wall2
	model = glm::translate(glm::mat4(1.0f), glm::vec3(1.0f, -0.5f, -9.0f));
	model = glm::scale(model, glm::vec3(1.2f, 0.07f, 0.2f));
	model = glm::rotate(model, glm::radians(45.0f), glm::vec3(1.0f, 0.0f, 0.0f));
	//model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(1.0f, 0.0f, 0.0f));
	//send model matrix data to shader
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//create normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	wall.Draw(myCustomShader);

	//create model matrix for wall3
	model = glm::translate(glm::mat4(1.0f), glm::vec3(1.0f, -0.5f, 9.0f));
	model = glm::scale(model, glm::vec3(1.2f, 0.07f, 0.2f));
	model = glm::rotate(model, glm::radians(-45.0f), glm::vec3(1.0f, 0.0f, 0.0f));
	//model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(1.0f, 0.0f, 0.0f));
	//send model matrix data to shader
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//create normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	wall.Draw(myCustomShader);

	//create model matrix for stone
	model = glm::translate(glm::mat4(1.0f), glm::vec3(6.0f, -0.9f, 0.1f));
	model = glm::scale(model, glm::vec3(0.001f, 0.0006f, 0.002f));
	model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(0.0f, 1.0f, 0.0f));
	//send model matrix data to shader
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//create normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	stone.Draw(myCustomShader);

	//create model matrix for stone
	model = glm::translate(glm::mat4(1.0f), glm::vec3(2.0f, -0.92f, 4.0f));
	model = glm::scale(model, glm::vec3(0.002f, 0.0012f, 0.004f));
	model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(0.0f, 1.0f, 0.0f));
	//send model matrix data to shader
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//create normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	stone.Draw(myCustomShader);

	//create model matrix for stone1
	model = glm::translate(glm::mat4(1.0f), glm::vec3(-6.0f, -0.92f, 2.0f));
	model = glm::scale(model, glm::vec3(0.002f, 0.0012f, 0.004f));
	model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(0.0f, 1.0f, 0.0f));
	//send model matrix data to shader
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//create normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	stone.Draw(myCustomShader);

	//create model matrix for stone2
	model = glm::translate(glm::mat4(1.0f), glm::vec3(-6.0f, -0.92f, 1.5f));
	model = glm::scale(model, glm::vec3(0.002f, 0.0012f, 0.004f));
	model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(0.0f, 1.0f, 0.0f));
	//send model matrix data to shader
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//create normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	stone.Draw(myCustomShader);

	//create model matrix for stone3
	model = glm::translate(glm::mat4(1.0f), glm::vec3(-6.0f, -0.92f, 1.0f));
	model = glm::scale(model, glm::vec3(0.002f, 0.0012f, 0.004f));
	model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(0.0f, 1.0f, 0.0f));
	//send model matrix data to shader
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//create normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	stone.Draw(myCustomShader);

	//create model matrix for frog
	model = glm::translate(glm::mat4(1.0f), glm::vec3(-6.0f, -0.78f, 2.0f));
	model = glm::scale(model, glm::vec3(0.005f, 0.003f, 0.01f));
	model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(1.0f, 0.0f, 0.0f));
	model = glm::rotate(model, glm::radians(-90.0f), glm::vec3(0.0f, 0.0f, 1.0f));
	//send model matrix data to shader
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//create normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	frog.Draw(myCustomShader);

	//create model matrix for lamp
	model = glm::translate(glm::mat4(1.0f), glm::vec3(4.3f, -0.8f, 2.7f));
	model = glm::scale(model, glm::vec3(0.1f, 0.1f, 0.1f));
	//send model matrix data to shader
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//create normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	lamp.Draw(myCustomShader);

	//draw a white cube around the light

	lightShader.useShaderProgram();

	glUniformMatrix4fv(glGetUniformLocation(lightShader.shaderProgram, "view"), 1, GL_FALSE, glm::value_ptr(view));

	model = glm::rotate(glm::mat4(1.0f), glm::radians(lightAngle), glm::vec3(0.0f, 1.0f, 0.0f));
	model = glm::rotate(glm::mat4(1.0f), glm::radians(lightAngle1), glm::vec3(0.0f, 0.0f, 1.0f));
	model = glm::translate(model, lightDir);
	model = glm::scale(model, glm::vec3(0.05f, 0.05f, 0.05f));
	glUniformMatrix4fv(glGetUniformLocation(lightShader.shaderProgram, "model"), 1, GL_FALSE, glm::value_ptr(model));

	lightCube.Draw(lightShader);

	mySkyBox.Draw(skyboxShader, view, projection);

}

int main(int argc, const char * argv[]) {

	initOpenGLWindow();
	initOpenGLState();
	initFBOs();
	initModels();
	initShaders();
	initUniforms();	
	glCheckError();

	while (!glfwWindowShouldClose(glWindow)) {
		renderScene();

		glfwPollEvents();
		glfwSwapBuffers(glWindow);
	}

	//close GL context and any other GLFW resources
	glfwTerminate();

	return 0;
}
